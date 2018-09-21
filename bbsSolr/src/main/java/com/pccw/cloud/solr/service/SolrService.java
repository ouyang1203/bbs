package com.pccw.cloud.solr.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrService {
	
	private static Logger log_ = LoggerFactory.getLogger(SolrService.class);
	
	public static void main(String[] args) {
		String url = "http://localhost:8983/solr/bbsCore";
		//构建solr客户端
		HttpSolrClient.Builder builder = new HttpSolrClient.Builder(url);
		HttpSolrClient client = builder.build();
		//封装查询对象
		SolrQuery query = new SolrQuery(":");
		query.setQuery("productName:*海尔*");
		try {
			QueryResponse res = client.query(query);
			SolrDocumentList list = res.getResults();
			for(SolrDocument document :list) {
				String id = (String)document.getFirstValue("id");
				String productName = (String)document.getFirstValue("productName");
				String productDescription = (String)document.getFirstValue("productDescription");
				log_.info("id is {},productName is {},productDescription is {}",id,productName,productDescription);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
