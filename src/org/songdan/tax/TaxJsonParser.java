package org.songdan.tax;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 从Json数据格式中解析数据
 * Created by SongDan on 2017/5/17.
 */
public class TaxJsonParser {

    private String fileName;

    public TaxJsonParser(String fileName) {
        this.fileName = fileName;
    }

    public TreeMap<String,TaxCategory> parse() throws IOException {
        Gson gson = new GsonBuilder().create();
        TreeMap<String,TaxCategory> taxMap =new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File(this.getClass().getResource(fileName).getPath())));
        String json = null;
        while ((json = reader.readLine()) != null) {
            TaxCategory taxCategory = gson.fromJson(json, TaxCategory.class);
            taxMap.put(taxCategory.getMergeCoding(), taxCategory);
        }
        return taxMap;
    }

    public static void main(String[] args) throws IOException {
        Map<String, TaxCategory> taxMap = new TaxJsonParser("tax.json").parse();
        Assert.assertEquals(4193, taxMap.size());
    }

}
