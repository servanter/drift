package com.zhy.drift.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class Tools {

    public static final String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException {
        if (in == null)
            return "";

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        return out.toString();
    }

    public static final boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        List<String> params = new ArrayList<String>();
        params.add(token);
        params.add(timestamp);
        params.add(nonce);
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String temp = params.get(0) + params.get(1) + params.get(2);
        return SHA1.encode(temp).equals(signature);
    }

    public static Document getDocumentByInputStream(InputStream in) {
        String postXML = intoString(in);
        StringReader re = new StringReader(postXML);
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(re);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static String intoString(InputStream in) {
        if(in == null)
            return "";
        
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = in.read(b)) != -1;) {
                out.append(new String(b, 0, n, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

}
