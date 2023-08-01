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

import org.springframework.stereotype.Service;

@Service
public class TheTwoStages {

	/**
	 * stage1:solution
	 * 
	 * @args
	 */
	public static String removeAAA(String str) {
		if (str.isEmpty()) {
			return "";
		}
		char ch = str.charAt(0);
		String newStr = "";
		int cnt = 0;
		String tmp = "";
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
		}
		// For distinct character strings or strings that not longer than 2 digits,
		// return ""
		// else leave it for next recursive
		if (newStr.chars().distinct().count() == 1) {
			if (newStr.length() > 2) {
				return "";
			} else {
				return newStr;
			}
		} else {
			if (!tmp.isEmpty()) {
				lst.add(tmp);
			} else {
				return newStr;
			}
			for (String lsitem : lst) {
				newStr = newStr.replace(lsitem, "");
			}
		}
		return removeAAA(newStr);
	}

	public String removeBBB(String str) {
		char ch = str.charAt(0);

		if (str.chars().distinct().count() == 1) {
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
		}

		if (newStr.length() < 3 || "".equals(tmp) || newStr.equals(tmp)) {
			return newStr;
		} else {
			if (!"".equals(tmp)) {
				lst.add(tmp);
			}
			for (int j = 0; j < lst.size(); j++) {
				String lstItem = lst.get(j);
				if (!lstItem.isEmpty()) {
					String singleChr = "";
					if (lstItem.startsWith("a")) {
						singleChr = "";
					} else {
						singleChr = String.valueOf((char) ((int) lstItem.charAt(0) - 1));
					}
					newStr = newStr.replace(lst.get(j), singleChr);
				}
			}

			if ("".equals(tmp)) {
				return newStr;
			} else {
				return removeBBB(newStr);
			}
		}
	}
}
