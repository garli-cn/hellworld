/** 
  ***************************************************************  
  * 
  * 作者： Gary Li
  * 描述： 包含stage1 -> removeAAA()和stage2->removeBBB()对应的处理方法
  * 备注：
  * @version 
   ***************************************************************  */
package com.newfeatures.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class TheTwoStages {

	/**
	 * stage1:solution
	 * 
	 * @args
	 */
	public String removeAAA(String str) {
		if (str.isEmpty()) {
			return "";
		}
		char ch = str.charAt(0);
		String newStr = "",tmp = "";
		int cnt = 0;
		List<String> lst = new ArrayList<String>();
		newStr += ch;
		for (int i = 1; i < str.length(); i++) {
			// recursively to check from the second character in the string with its
			// previous one
			// and find if they are identical, if yes, add one to its counter. loop out all
			// those
			// 3 or more consecutive identical substrings.

			if (str.charAt(i) == ch) {
				cnt++;
				if (cnt > 1) {
					tmp = String.format("%" + (cnt + 1) + "s", "").replace(' ', ch);
				}
			} else {
				if (cnt > 1) {
					lst.add(tmp);
				}
				cnt = 0;
			}
			newStr = newStr + str.charAt(i);
			ch = str.charAt(i);
			if((i==str.length()-1) && !tmp.isEmpty()) {
				lst.add(tmp);
			}
		}
		// For distinct character strings or strings that not longer than 2 digits,
		// return ""
		// else leave it for next recursive
		if ( this.isDistinct(newStr) ) {
			if (newStr.length() > 2) {
				return "";
			} else {
				return newStr;
			}
		} else {
			if (tmp.isEmpty()) {
				return newStr;
			}
			for (String lsitem : lst) {
				newStr = this.replaceChars(newStr,lsitem, 1);
			}
		}
		return removeAAA(newStr);
	}

	public String removeBBB(String str) {
		char ch = str.charAt(0);

		if (this.isDistinct(str)) {
			if (ch == 'a' && str.length() > 2) {
				return "";
			} else if (str.length() < 3) {
				return str;
			} else {
				return String.valueOf((char) ((int) ch - 1));
			}
		}
		String newStr = "";
		int cnt = 0;
		String tmp = "";
		List<String> lst = new ArrayList<String>();
		newStr += ch;

		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == ch) {
				cnt++;
				if (cnt > 1) {
					tmp = String.format("%" + (cnt + 1) + "s", "").replace(' ', ch);
				}
			} else {
				if (cnt > 1 && !tmp.startsWith("a")) {
					lst.add(tmp);
				}
				cnt = 0;
			}
			newStr = newStr + str.charAt(i);
			ch = str.charAt(i);
			if((i==str.length()-1) && !tmp.isEmpty()) {
				lst.add(tmp);
			}
		}

			for (String lstitem : lst) {
				if (!lstitem.isEmpty()) {
					newStr = this.replaceChars(newStr,lstitem, 2);;
				}
			}

			if ("".equals(tmp)) {
				return newStr;
			} else {
				return removeBBB(newStr);
			}
	}
	
	//check if the chars is distinct
	private boolean isDistinct(String str) {
		return str.chars().distinct().count() == 1;
	}
	
	//replace substring with different chars according the input type
	private String replaceChars(String str, String tmp, int type) {
		String rtnStr = switch (type) {
			case 1 -> str.replace(tmp, "");//str.length()>2?"":str
			case 2 -> str.replace(tmp,(tmp.charAt(0)=='a')?"":String.valueOf((char) ((int) tmp.charAt(0) - 1)));
			default -> str;
		};
		return rtnStr;
	}
	
}
