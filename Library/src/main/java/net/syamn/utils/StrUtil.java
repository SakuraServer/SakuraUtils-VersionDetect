/**
 * SakuraUtils - Package: net.syamn.utils Created: 2012/12/22 18:19:58
 */
package net.syamn.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * StrUtil (StrUtil.java)
 * 
 * @author syam(syamn)
 */
public class StrUtil {
    /**
     * PHPの join(array, delimiter) と同じ関数
     * @param s 結合するコレクション
     * @param delimiter デリミタ文字
     * @return 結合後の文字列
     */
    public static String join(Collection<?> s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator<?> iter = s.iterator();

        // 要素が無くなるまでループ
        while (iter.hasNext()) {
            buffer.append(iter.next());
            // 次の要素があればデリミタを挟む
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        // バッファ文字列を返す
        return buffer.toString();
    }
}
