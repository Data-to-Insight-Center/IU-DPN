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
package org.dataconservancy.ui.it.support;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: HanhVu
 * Date: 2/7/12
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewCollectionDetailsRequest {
    private final UiUrlConfig urlConfig;
    private String collectionIdToView;

    private static final String STRIPES_EVENT = "viewCollectionDetails";

    public ViewCollectionDetailsRequest(UiUrlConfig urlConfig) {
        if (urlConfig == null) {
            throw new IllegalArgumentException("UiUrlConfig must not be null.");
        }
        this.urlConfig = urlConfig;
    }
    public void setCollectionIdToView(String collectionIdToView){
        this.collectionIdToView = collectionIdToView;
    }

    public HttpPost asHttpPost() {

        if(collectionIdToView == null){
            throw new RuntimeException("Id of the collection to be viewed has not been set. Call setCollectionIdToView() first");
        }

        HttpPost post = null;
        try {
            post = new HttpPost(urlConfig.getViewCollectionUrl(collectionIdToView).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("selectedCollectionId", "collectionIdToView"));
        params.add(new BasicNameValuePair(STRIPES_EVENT, "View Collections Details"));

        HttpEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        post.setEntity(entity);

        return post;
    }
}
