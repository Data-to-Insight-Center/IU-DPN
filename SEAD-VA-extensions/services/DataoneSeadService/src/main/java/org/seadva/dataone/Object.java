/*
 * Copyright 2013 The Trustees of Indiana University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.seadva.dataone;

import org.dataconservancy.dcs.index.dcpsolr.DcsSolrField;
import org.dataconservancy.dcs.index.dcpsolr.DcsSolrField.EntityTypeValue;
import org.dataconservancy.dcs.index.dcpsolr.SeadSolrField;
import org.dataconservancy.dcs.index.solr.support.SolrQueryUtil;
import org.dataconservancy.dcs.query.api.QueryMatch;
import org.dataconservancy.dcs.query.api.QueryResult;
import org.dataconservancy.dcs.query.api.QueryServiceException;
import org.dataconservancy.model.dcs.*;
import org.dataone.service.types.v1.*;
import org.jibx.runtime.JiBXException;
import org.seadva.model.SeadEvent;
import org.seadva.model.SeadFile;
import org.seadva.model.pack.ResearchObject;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Returns list of objects and also datastream for individual objects
*/

@Path("/mn/v1/object")
public class Object{

    public Object() throws IOException, SAXException, ParserConfigurationException {
    }

    @Context
    ServletContext context;

    @GET
    @Path("{objectId}")
    @Produces("*/*")
    public Response getObject(@Context HttpServletRequest request,
                              @HeaderParam("user-agent") String userAgent,
                              @PathParam("objectId") String objectId) throws IOException {


        String test ="<error name=\"NotFound\" errorCode=\"404\" detailCode=\"1020\" pid=\""+URLEncoder.encode(objectId)+"\" nodeId=\""+SeadQueryService.NODE_IDENTIFIER+"\">\n" +
                "<description>The specified object does not exist on this node.</description>\n" +
                "<traceInformation>\n" +
                "method: mn.get hint: http://cn.dataone.org/cn/resolve/"+URLEncoder.encode(objectId)+"\n" +
                "</traceInformation>\n" +
                "</error>";

        String id = objectId;



        QueryResult<DcsEntity> result = null;
        try {
            result = SeadQueryService.queryService.query(SolrQueryUtil
                    .createLiteralQuery("resourceValue",
                            objectId), 0, -1); //sort by filename
        } catch (QueryServiceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        List<QueryMatch<DcsEntity>> matches = result.getMatches();
        for(QueryMatch<DcsEntity> entity: matches){
            DcsFile dcsFile = (DcsFile)entity.getObject();
            //String filePath = dcsFile.getSource().replace("file://","").replace("file:","").replace(":/","://").replace(":///","://");
            String filePath = SeadQueryService.datastreamURL +  URLEncoder.encode(dcsFile.getId());


            URL url = new URL(filePath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = urlConnection.getInputStream();

            String lastFormat = null;
            if(dcsFile.getFormats().size()>0){
                for(DcsFormat format:dcsFile.getFormats()){
                    if(SeadQueryService.sead2d1Format.get(format.getFormat())!=null) {
                        lastFormat = SeadQueryService.mimeMapping.get(SeadQueryService.sead2d1Format.get(format.getFormat()));
                        break;
                    }
                    lastFormat = SeadQueryService.mimeMapping.get(format.getFormat());
                }
            }
            Response.ResponseBuilder responseBuilder = Response.ok(is);

            responseBuilder.header("DataONE-SerialVersion","1");

            if(lastFormat!=null){
                String[] format = lastFormat.split(",");
                if(format.length>0)
                {
                    responseBuilder.header("Content-Type", format[0]);
                    responseBuilder.header("Content-Disposition",
                            "inline; filename=" + id+format[1]);
                }
                else{
                    responseBuilder.header("Content-Disposition",
                            "inline; filename=" + id);
                }
            }
            else{
                responseBuilder.header("Content-Disposition",
                        "inline; filename=" + id);
            }

            String ip = null;
            if(request!=null)
                ip = request.getRemoteAddr();
            SeadEvent readEvent  = SeadQueryService.dataOneLogService.creatEvent(SeadQueryService.d1toSeadEventTypes.get(Event.READ.xmlValue()), userAgent, ip, entity.getObject());

            ResearchObject eventsSip = new ResearchObject();
            eventsSip.addEvent(readEvent);
            SeadQueryService.dataOneLogService.indexLog(eventsSip);

            return responseBuilder.build();
        }
        throw new NotFoundException(test);
    }



    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String listObjects(@Context HttpServletRequest request,
                                   @HeaderParam("user-agent") String userAgent,
                                   @QueryParam("start") int start,
                                   @QueryParam("count") String countStr,
                                   @QueryParam("formatId") String formatId,
                                   @QueryParam("fromDate") String fromDate,
                                   @QueryParam("toDate") String toDate)
            throws QueryServiceException, JiBXException, ParseException, TransformerException {



        Map<String,Integer> doiCount = new HashMap<String, Integer>();

        String queryStr = SolrQueryUtil.createLiteralQuery("entityType", EntityTypeValue.FILE.solrValue())
                +" AND "+SolrQueryUtil.createLiteralQuery("resourceType", "dataone");

        if(formatId!=null) {
            String tempFormat = SeadQueryService.d12seadFormat.get(formatId);
            if(tempFormat ==null)
                tempFormat = formatId;

            queryStr+= " AND "+SolrQueryUtil.createLiteralQuery(DcsSolrField.FormatField.FORMAT.solrName(), tempFormat);
        }

        if(fromDate!=null&&toDate!=null) {
            fromDate = fromDate.replace("+00:00","Z");
            toDate = toDate.replace("+00:00","Z");
            queryStr+= " AND "+ SeadSolrField.EntityField.MDUPDATE_DATE.solrName() + ":" + "[" + fromDate+" TO " +toDate+ "]";
        }
        else if(fromDate!=null) {
            fromDate = fromDate.replace("+00:00","Z");
            queryStr+= " AND "+ SeadSolrField.EntityField.MDUPDATE_DATE.solrName() + ":" + "[" + fromDate+" TO NOW" + "]";
        }
        else if(toDate!=null) {
            toDate = toDate.replace("+00:00","Z");
            queryStr+= " AND "+ SeadSolrField.EntityField.MDUPDATE_DATE.solrName() + ":" + "[ NOW-365DAY TO " + toDate + "]";
        }

        int count = 0;
        boolean countZero = false;
        if(countStr!=null){
            count = Integer.parseInt(countStr);
            if(count <= 0)
                countZero = true;
        }

        QueryResult<DcsEntity> result = SeadQueryService.queryService.query(
                queryStr
                , start, count
        );    //add sort to the query by file name      + "&sort=fileName+asc"

        List<QueryMatch<DcsEntity>> matches = result.getMatches();


        ObjectList objectList = new ObjectList();

        if(countZero){
            objectList.setCount(0);
            objectList.setTotal((int)result.getTotal());
            objectList.setStart(start);
            return SeadQueryService.marshal(objectList);
        }

        for(QueryMatch<DcsEntity> entity: matches){
            SeadFile dcsFile = (SeadFile)entity.getObject();



            String date =  dcsFile.getMetadataUpdateDate();
            ObjectInfo objectInfo =  new ObjectInfo();
            Identifier identifier = new Identifier();
            String id = null;
            Collection<DcsResourceIdentifier> altIds = dcsFile.getAlternateIds();
            for(DcsResourceIdentifier altId: altIds){
                if(altId.getTypeId().equalsIgnoreCase("dataone")){
                    id = altId.getIdValue().replace("http://dx.doi.org/","doi-");
                    int index;
                    if(doiCount.containsKey(id)){
                        index = doiCount.get(id);
                        index ++;
                    }
                    else
                        index = 1;

                    doiCount.put(id, index);
                    //  id += "_" + index;
                    break;
                }
            }
            if(id==null)
                id = dcsFile.getId();
            identifier.setValue(id);//URLEncoder.encode(id));
            objectInfo.setIdentifier(identifier);
            objectInfo.setSize(BigInteger.valueOf(dcsFile.getSizeBytes() < 0 ? 10 : dcsFile.getSizeBytes()));

            String lastFormat = "TestFormatId";
            if(dcsFile.getFormats().size()>0){
                for(DcsFormat format:dcsFile.getFormats()){
                    if(SeadQueryService.sead2d1Format.get(format.getFormat())!=null) {
                        ObjectFormatIdentifier formatIdentifier = new ObjectFormatIdentifier();
                        formatIdentifier.setValue(SeadQueryService.sead2d1Format.get(format.getFormat()));
                        objectInfo.setFormatId(formatIdentifier);
                        break;
                    }
                    lastFormat = format.getFormat();
                }
            }

            if(objectInfo.getFormatId()==null) {
                ObjectFormatIdentifier formatIdentifier = new ObjectFormatIdentifier();
                formatIdentifier.setValue(lastFormat);
                objectInfo.setFormatId(formatIdentifier);
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            objectInfo.setDateSysMetadataModified(simpleDateFormat.parse(date));

            Checksum checksum = new Checksum();
            checksum.setAlgorithm("MD5");
            checksum.setValue("testChecksum");

            if(dcsFile.getFixity().size()>0){
                DcsFixity[] fileFixity = dcsFile.getFixity().toArray(new DcsFixity[dcsFile.getFixity().size()]);

                for(int j=0;j<dcsFile.getFixity().size();j++){
                    if(fileFixity[j].getAlgorithm().equalsIgnoreCase("MD-5"))
                    {
                        checksum.setAlgorithm("MD5");
                        checksum.setValue(fileFixity[j].getValue());
                    }
                    if(fileFixity[j].getAlgorithm().equalsIgnoreCase("SHA-1"))
                    {
                        checksum.setAlgorithm("SHA-1");
                        checksum.setValue(fileFixity[j].getValue());
                        break;
                    }
                }
            }
            objectInfo.setChecksum(checksum);
            objectList.getObjectInfoList().add(objectInfo);

        }
            objectList.setCount(result.getMatches().size());
        objectList.setTotal((int)result.getTotal());
        objectList.setStart(start);
        return SeadQueryService.marshal(objectList);
    }
}
