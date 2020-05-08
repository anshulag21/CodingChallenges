package com.solr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
//import org.apache.solr.client.solrj.response.schema.SchemaResponse.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.NamedList;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SolrUtils {
	// String urlString = "http://localhost:8983/solr/anshul/select?q=*%3A*";
	// SolrClient solrclient = new HttpSolrClient.Builder(urlString).build();
//public static void main(String[] args) throws Exception {
//	new SolrUtils().getDetails();
//}
	public JSONArray getDetails() throws Exception {

		SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/anshul").build();
		ModifiableSolrParams params = new ModifiableSolrParams();
		params.add("rows", "100");
		params.set("q", "*");
		// params.set("q", "*%3A*");
		params.set("start", 13);
		// return params.toString();

		QueryResponse response = client.query(params);

		Gson gson = new Gson();
//		String jsonstr = gson.toJson(response.getResults());
//		// System.out.println(jsonstr);
//		// return null;
//		JSONObject obj1 = new JSONObject();
//		obj1.put("Values", jsonstr);
//		// System.out.println(obj1.toString());
//		return obj1;
		return new JSONArray(gson.toJson(response.getResults()));

		// System.out.println(".................test......................." + jsonstr);
		// SolrDocumentList results = response.getResults();
		// JSONObject responseObj = this.iterateSolrDocumentList(results);
		// responseObj.put("numFound", results.getNumFound());
		// return responseObj;
		// return client.query(null); -- not used
		// return jsonstr;
		// SolrQuery query = new SolrQuery();
		// query.set("q", "price:599.99");
		// try {
		// QueryResponse response = solr.query(query);
		// } catch (SolrServerException | IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

//	private JSONObject iterateSolrDocumentList(SolrDocumentList documentList) throws JSONException {
//		ArrayList<HashMap<String, Object>> hitsOnPage = new ArrayList<HashMap<String, Object>>();
//		JSONObject documentListJson = new JSONObject();
//		int count = 1;
//
//		for (SolrDocument d : documentList) {
//			HashMap<String, Object> values = new HashMap<String, Object>();
//
//			for (Iterator<Map.Entry<String, Object>> i = d.iterator(); i.hasNext();) {
//				Map.Entry<String, Object> e2 = i.next();
//
//				values.put(e2.getKey(), e2.getValue());
//			}
//
//			hitsOnPage.add(values);
//			String key = "doc-" + count;
//			documentListJson.put(key, values);
//			count++;
//
//		}
//		return documentListJson;
//	}

//	public JSONObject addDocument(String firstname, String lastname, String id) throws Exception {
//		JSONObject responseJson = new JSONObject();
//        String id1;
//        String firstname1;
//        String lastname1;
//		SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/anshul").build();
//		// SolrServer solr = new CommonsHttpSolrServer(SolrClient.SERVER_URL);
//		
//		// Create the solr input document to add
//		SolrInputDocument document = new SolrInputDocument();
//		document.addField("id", id1);
//		document.addField("firstname", firstname1);
//		document.addField("lastname", lastname1);
//
//		UpdateResponse response = client.add(document);
//
//		// LOGGER.info(response.getStatus());
//		// Remember to commit your changes!
//		client.commit();
//
//		if (response.getStatus() == 0) {
//			responseJson.put("response", "Added Successfully");
//		} else {
//			responseJson.put("response", "Not added");
//		}
//		return responseJson;
//	}
	// post data to Solr
	public NamedList<Object> postDetails(String FirstName, String LastName, String Detail, String EmpID)
			throws Exception {
		// TODO Auto-generated method stub
		SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/anshul").build();
		SolrInputDocument inputDoc = new SolrInputDocument();
		inputDoc.addField("Firstname", FirstName);
		inputDoc.addField("Lastname", LastName);
		inputDoc.addField("Detail", Detail);
		inputDoc.addField("EmpID", EmpID);
		// inputDoc.addField("ID", id);

		client.add(inputDoc);
		UpdateResponse response = client.commit();
		//getDetails();
		return response.getResponse();
	}

}