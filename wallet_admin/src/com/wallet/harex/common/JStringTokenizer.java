package com.wallet.harex.common;

import java.util.*;

public class JStringTokenizer {

  private String data = null;
  private String delimiter = null;

  public JStringTokenizer(String str, String del) {
    data = str;
    delimiter = del;
  }

  public static ArrayList<String> getStrings(String str, String del) {
    JStringTokenizer tokens = new JStringTokenizer(str, del);
    return tokens.getStrings();
  }

  public synchronized ArrayList<String> getStrings() {
    ArrayList<String> ret = new ArrayList<String>();
    String buffer = new String(data);
    int sz = buffer.length();
    int i = 0;
    String column = null;
    
    while(true) {
      int inx = buffer.indexOf(delimiter, i);

      if(inx < 0) {
        column = buffer.substring(i).trim();
      } else {
        column = buffer.substring(i, inx).trim();
        i = inx + 1;
      }

      ret.add(column);
      if(inx < 0) break;

      if(i >= sz)
      {
        ret.add("");
        break;
      }
    }

    return ret;
  }
}
