/*
 * Copyright 2014 The Trustees of Indiana University
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

package org.seadva.registry.client;


import com.sun.jersey.test.framework.JerseyTest;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.junit.Test;
import org.seadva.registry.database.model.obj.vaRegistry.*;
import org.seadva.registry.service.util.QueryAttributeType;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class RegistryClientTest extends JerseyTest {

    RegistryClient client;

    public RegistryClientTest() throws Exception {
        super("org.seadva.registry");
        client = new RegistryClient("http://localhost:8080/registry/rest");

    }

    @Test
    public void testQueryByProperty() throws IOException, ClassNotFoundException {

     /*   //Create collection metadata
        org.seadva.registry.database.model.obj.vaRegistry.Collection collection = new org.seadva.registry.database.model.obj.vaRegistry.Collection();
        collection.setId(UUID.randomUUID().toString());
        collection.setName("test");
        collection.setVersionNum("1");
        collection.setIsObsolete(0);
        collection.setEntityName("test");
        collection.setEntityCreatedTime(new Date());
        collection.setEntityLastUpdatedTime(new Date());
        collection.setState(client.getStateByName("PublishedObject"));

        //Add alternate Id to collection
        DataIdentifier alternateId = new DataIdentifier();
        DataIdentifierPK idPK = new DataIdentifierPK();
        idPK.setDataIdentifierType(client.getDataIdentifierType("dpnObjectId"));
        BaseEntity temp = new
                BaseEntity();
        temp.setId(collection.getId());
        idPK.setEntity(temp);
        alternateId.setId(idPK);
        alternateId.setDataIdentifierValue(UUID.randomUUID().toString());
        collection.addDataIdentifier(alternateId);

        //Add metadata/ property to colletion
        Property property = new Property();
        property.setMetadata(client.getMetadataByType("abstract"));
        String metadataValue = "value_1.2:" + UUID.randomUUID().toString();
        property.setValuestr(metadataValue);
        collection.addProperty(property);

        //Insert collection
        client.postCollection(collection);*/

        //Query by metadata/property
//        List<BaseEntity> entityList = client.queryByProperty("abstract", metadataValue, QueryAttributeType.PROPERTY);
        List<BaseEntity> entityList = client.queryByProperty("CorrelationID", "fff3775b-f64a-404c-bd5c-6d429c68f4dd", QueryAttributeType.PROPERTY);
        //View returned Alternate ID of the retrieved collection, whose abstract property was matched
        Iterator returnedIds = entityList.get(0).getDataIdentifiers().iterator();
        while (returnedIds.hasNext()) {

            DataIdentifier returnedId  = (DataIdentifier) returnedIds.next();
            System.out.println("Identifier value:" + returnedId.getDataIdentifierValue());
            System.out.println("Identifier type:" + returnedId.getId().getDataIdentifierType().getDataIdentifierTypeName());
            returnedIds.remove();
        }

        assertTrue(entityList.size() > 0);
    }


    @Test
    public void testUpdateProperty() throws IOException {

        List<BaseEntity> entityList = client.queryByProperty("abstract", "value_1.0", QueryAttributeType.PROPERTY);
        for(BaseEntity entity:entityList){

            Iterator props = entity.getProperties().iterator();
            Set<Property> updatesProperties = new HashSet<Property>();
            while (props.hasNext()){
                Property property = (Property) props.next();
                if(property.getMetadata().getMetadataElement().contains("abstract"))
                    property.setValuestr("value_2.0");
                updatesProperties.add(property);
                props.remove();
            }

            entity.setProperties(updatesProperties);
            client.postEntity(entity);
        }
        List<BaseEntity> entityList2 = client.queryByProperty("abstract", "value_2.0", QueryAttributeType.PROPERTY);
        assertTrue(entityList2.size()>0);
    }

    @Test
    public void testQueryByDataIdentifier() throws IOException, ClassNotFoundException {

        org.seadva.registry.database.model.obj.vaRegistry.Collection collection = new org.seadva.registry.database.model.obj.vaRegistry.Collection();
        String altId = "fakedoi:"+UUID.randomUUID().toString();
        collection.setId(UUID.randomUUID().toString());
        collection.setName("test");
        collection.setVersionNum("1");
        collection.setIsObsolete(0);
        collection.setEntityName("test");
        collection.setEntityCreatedTime(new Date());
        collection.setEntityLastUpdatedTime(new Date());
        collection.setState(client.getStateByName("PublishedObject"));

        DataIdentifier dataIdentifier = new DataIdentifier();
        DataIdentifierPK dataIdentifierPK = new DataIdentifierPK();
        dataIdentifierPK.setDataIdentifierType(client.getDataIdentifierType("doi"));
        dataIdentifier.setId(dataIdentifierPK);
        dataIdentifier.setDataIdentifierValue(altId);
        collection.addDataIdentifier(dataIdentifier);

        client.postCollection(collection);

        List<BaseEntity> entityList = client.queryByProperty(null, altId, QueryAttributeType.DATA_IDENTIFIER);
        assertTrue(entityList.size() > 0);
    }

    @Test
    public void testPostAgent() throws IOException {
        Agent agent = new Agent();
        agent.setId(UUID.randomUUID().toString());
        agent.setEntityName("test user");
        agent.setEntityLastUpdatedTime(new Date());
        agent.setEntityCreatedTime(new Date());
        agent.setFirstName("test");
        agent.setLastName("test");

        client.postAgent(agent, "Curator");
    }

}