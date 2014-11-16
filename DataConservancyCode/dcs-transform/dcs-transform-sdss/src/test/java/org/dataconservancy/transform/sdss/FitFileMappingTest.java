/*
 * Copyright 2012 Johns Hopkins University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dataconservancy.transform.sdss;

import java.net.URI;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import org.dataconservancy.model.gqm.DateTimeInterval;
import org.dataconservancy.model.gqm.GQM;
import org.dataconservancy.model.gqm.Geometry;
import org.dataconservancy.model.gqm.Location;
import org.dataconservancy.model.gqm.Point;
import org.dataconservancy.model.gqm.Relation;
import org.dataconservancy.index.gqmpsql.SpatialReferenceSystem;
import org.dataconservancy.model.dcp.Dcp;
import org.dataconservancy.model.dcs.DcsDeliverableUnit;
import org.dataconservancy.model.dcs.DcsMetadata;
import org.dataconservancy.transform.Output;

import junit.framework.Assert;

public class FitFileMappingTest {
    private final String XML = 
        "<VOTABLE xmlns=\"http://www.ivoa.net/xml/VOTable/v1.1\" version=\"1.1\">\n" +
            "<DESCRIPTION>\n" +
                "VO Table generated for Data Conservancy Y1P by the ivoa-metadata-extractor\n" +
            "</DESCRIPTION>\n" +
            "<RESOURCE type=\"results\">\n" +
                "<DESCRIPTION>Conversion of FITS keywords to VOTable</DESCRIPTION>\n" +
                "<INFO name=\"QUERY_STATUS\" value=\"OK\"/>\n" +
                    "<TABLE name=\"FITS Image Keywords\">\n" +
                        "<FIELD name=\"Image Title\" datatype=\"char\" ucd=\"VOX:Image_Title\"/>\n" +
                        "<FIELD name=\"Declination\" datatype=\"double\" ucd=\"POS_EQ_DEC_MAIN\"/>\n" +
                        "<FIELD name=\"Right Ascension\" datatype=\"double\" ucd=\"POS_EQ_RA_MAIN\"/>\n" +
                        "<FIELD name=\"Number of axes\" datatype=\"int\" ucd=\"VOX:Image_Naxes\"/>\n" +
                        "<FIELD name=\"Axes length\" datatype=\"int\" ucd=\"VOX:Image_Naxis\"/>\n" +
                        "<FIELD name=\"Image scale\" datatype=\"double\" ucd=\"VOX:Image_Scale\"/>\n" +
                        "<FIELD name=\"Image format\" datatype=\"char\" ucd=\"VOX:Image_Format\"/>\n" +
                        "<FIELD name=\"Image URL\" datatype=\"char\" ucd=\"VOX:Image_AccessReference\"/>\n" +
                        "<FIELD name=\"Instrument ID\" datatype=\"char\" ucd=\"INST_ID\"/>\n" +
                        "<FIELD name=\"Mean date of observation\" datatype=\"double\" ucd=\"VOX:Image_MJDateObs\"/>\n" +
                        "<FIELD name=\"STC Coordinate Reference Frame\" datatype=\"char\" ucd=\"VOX:STC_CoordRefFrame\"/>\n" +
                        "<FIELD name=\"WCS Equinox\" datatype=\"double\" ucd=\"VOX:STC_CoordEquinox\"/>\n" +
                        "<FIELD name=\"WCS Projection\" datatype=\"char\" ucd=\"VOX:WCS_CoordProjection\"/>\n" +
                        "<FIELD name=\"WCS Reference Pixel\" datatype=\"double\" ucd=\"VOX:WCS_CoordRefPixel\"/>\n" +
                        "<FIELD name=\"WCS Reference Value\" datatype=\"double\" ucd=\"VOX:WCS_CoordRefValue\"/>\n" +
                        "<FIELD name=\"WCS CD Matrix\" datatype=\"double\" ucd=\"VOX:WCS_CDMatrix\"/>\n" +
                        "<FIELD name=\"Image processing flag(s)\" datatype=\"char\" ucd=\"VOX:Image_PixFlags\"/>\n" +
                        "<FIELD name=\"Estimated image size (bytes)\" datatype=\"int\" ucd=\"VOX:Image_FileSize\"/>\n" +
                        "<DATA>\n" +
                            "<TABLEDATA>\n" +
                                "<TR>\n" +
                                    "<!-- Image Title -->\n" +
                                    "<TD>76 N</TD>\n" +
                                    "<!-- Declination -->\n" +
                                    "<TD>11.76305</TD>\n" +
                                    "<!-- Right Ascension -->\n" +
                                    "<TD>319.41711</TD>\n" +
                                    "<!-- Number of axes -->\n" +
                                    "<TD>2</TD>\n" +
                                    "<!-- Axes length (px) -->\n" +
                                    "<TD>1489,2048</TD>\n" +
                                    "<!-- Axes scale -->\n" +
                                    "<TD/>\n" +
                                    "<!-- Image format -->\n" +
                                    "<TD>image/fits</TD>\n" +
                                    "<!-- Image access url -->\n" +
                                    "<TD>http://das.sdss.org/imaging/1739/40/corr/6/fpC-001739-i6-0038.fit.gz</TD>\n" +
                                    "<!-- Instrument Id -->\n" +
                                    "<TD>2.5m</TD>\n" +
                                    "<!-- Mean Julian Date of observation -->\n" +
                                    "<TD>9.700128E11</TD>\n" +
                                    "<!-- WCS reference frame -->\n" +
                                    "<TD>FK5</TD>\n" +
                                    "<!-- WCS equinox -->\n" +
                                    "<TD>2000.0</TD>\n" +
                                    "<!-- WCS projection -->\n" +
                                    "<TD>TAN</TD>\n" +
                                    "<!-- WCS reference pixel -->\n" +
                                    "<TD>1024.5,744.5</TD>\n" +
                                    "<!-- WCS reference value -->\n" +
                                    "<TD>319.38043952,11.78769285</TD>\n" +
                                    "<!-- WCS CD Matrix -->\n" +
                                    "<TD>-2.0339869491289E-5 ,1.08049715344603E-4 ,1.08123022460938E-4 ,2.03373857526878E-5</TD>\n" +
                                    "<!-- Processing flags -->\n" +
                                    "<TD>C</TD>\n" +
                                    "<!-- Estimated size in bytes -->\n" +
                                    "<TD>6099840</TD>\n" +
                                "</TR>\n" +
                            "</TABLEDATA>\n" +
                        "</DATA>\n" +
                    "</TABLE>\n" +
                "</RESOURCE>\n" +
        "</VOTABLE>";
    
    private final String ENTITY_ID = "entityID";
    private GQM gqm;
    private Dcp sip;
    
    private static class GQMTestOutput implements Output<String, GQM> {

        Map<String, GQM> results = new HashMap<String, GQM>();
        
        public void write(String key, GQM value) {
            results.put(key, value);
        }
        
        @Override
        public void close() {
            /* Does nothing */
        }

    }
    
    @Before
    public void setUp() {
        gqm = new GQM(ENTITY_ID);
        double declination = 11.76305;
        double rightAscension = 319.41711;
        
        Point point = new Point(declination, rightAscension);
        Geometry geom = new Geometry(Geometry.Type.POINT, point);
        
        URI srid = SpatialReferenceSystem.forEquatorialCoordinateSystem("J2000");
        Location location = new Location(geom, srid);
        gqm.getLocations().add(location);
        
        long date = -185759379111600000l;
        DateTimeInterval dti = new DateTimeInterval(date, date);
        gqm.getIntervals().add(dti);
        
        String title = "76 N";          
        Relation relation = new Relation(URI.create("ImageTitle"), title);
        gqm.getRelations().add(relation);
        
        String url = "http://das.sdss.org/imaging/1739/40/corr/6/fpC-001739-i6-0038.fit.gz";
        Relation urlRelation = new Relation(URI.create("ImageURL"), url);
        gqm.getRelations().add(urlRelation);
        
        sip = new Dcp();
        DcsDeliverableUnit du = new DcsDeliverableUnit();
        DcsMetadata md = new DcsMetadata();
        
        md.setMetadata(XML);
        du.addMetadata(md);
        
        Set<String> creators = new HashSet<String>();
        creators.add("SDSS");
        du.setCreators(creators);
        
        sip.addDeliverableUnit(du);    
    }
    
    @Test
    public void testMapping(){
        FitFileMapping mapping = new FitFileMapping();
        
        GQM mappedGQM;
        GQMTestOutput output = new GQMTestOutput();
        
        mapping.map(ENTITY_ID, sip, output);
        
        mappedGQM = output.results.get(ENTITY_ID);
        
        Assert.assertEquals(gqm, mappedGQM);
        
    }  
}
