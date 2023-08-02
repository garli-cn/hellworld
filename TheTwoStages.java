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
	 * @args type: 1 for stage#1,  2 for stage#2
	 */
	public String removeAAA(String str, int type) {
		char ch = str.charAt(0);

		if (this.isDistinct(str)) {
			switch (type) {
				case 1 -> {
					if (str.length() > 2 || str.isEmpty()) {
						return "";
					} else {
						return str;
					}
				}
				case 2 -> {
					if (ch == 'a' && str.length() > 2) {
						return "";
					} else if (str.length() < 3) {
						return str;
					} else {
						return String.valueOf((char) ((int) ch - 1));
					}
				}
				default -> {
					System.out.println("Notification!: the input type is not correct");
				}
			};
		}
		String newStr = "", tmp = "";
		int cnt = 0;
		List<String> lst = new ArrayList<String>();
		newStr += ch;

		// recursively to check from the second character in the string with its
		// previous one
		// and find if they are identical, if yes, add one to its counter. loop out all
		// those
		// 3 or more consecutive identical substrings.
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == ch) {
				cnt++;
				if (cnt > 1) {
					tmp = String.format("%" + (cnt + 1) + "s", "").replace(' ', ch);
				}
			} else {
				switch (type) {
					case 1 -> {
						if (cnt > 1) {
							lst.add(tmp);
						}
					}
					case 2 -> {
						if (cnt > 1 && !tmp.startsWith("a")) {
							lst.add(tmp);
						}
					}
					default -> {
						System.out.println("Notification!: the input type is not correct");
					}
				};
				cnt = 0;
			}
			newStr = newStr + str.charAt(i);
			ch = str.charAt(i);
			if ((i == str.length() - 1) && !tmp.isEmpty()) {
				lst.add(tmp);
			}
		}

		for (String lstitem : lst) {
			if (!lstitem.isEmpty()) {
				newStr = this.replaceChars(newStr, lstitem, type);
			}
		}
		// For distinct character strings or strings that not longer than 2 digits,
		// return ""
		// else leave it for next recursive
		if (tmp.isEmpty()) {
			return newStr;
		} else {
			if (newStr.isEmpty()) {
				return newStr;
			}else {
				return removeAAA(newStr, type);
			}
		}
	}

	// check if the chars is distinct
	private boolean isDistinct(String str) {
		return str.chars().distinct().count() == 1;
	}

	// replace substring with different chars according the input type
	private String replaceChars(String str, String tmp, int type) {
		String rtnStr = switch (type) {
		case 1 -> str.replace(tmp, "");// str.length()>2?"":str
		case 2 -> str.replace(tmp, (tmp.charAt(0) == 'a') ? "" : String.valueOf((char) ((int) tmp.charAt(0) - 1)));
		default -> str;
		};
		return rtnStr;
	}

}
