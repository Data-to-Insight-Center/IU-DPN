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

package org.dataconservancy.dcs.access.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProvenaceDataset implements IsSerializable {
		public String dataSetId = null; //Id of a dataset
		public String datasetTitle = null;
		
		//Stores workflow instance id and related provenance. It is one-to-one map
		public Map<String,List<ProvenanceRecord>> provRecordbyWf = 
				new HashMap<String, List<ProvenanceRecord>>();

}	