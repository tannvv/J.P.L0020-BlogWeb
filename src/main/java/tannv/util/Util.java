/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.util;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author TanNV
 */
public class Util {

    public static String toSha256(String password) {
        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }

    public static int numberPage(int element, int size) {
        int result = 0;
        if (element <= 0) {
            throw new IllegalArgumentException();
        }
        result = element / size;
        if ((element % size) != 0) {
            result = result + 1;
        }
        return result;
    }

    public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
        java.sql.Date sqlDate = null;
        if (utilDate != null) {
            sqlDate = new java.sql.Date(utilDate.getTime());
        }
        return sqlDate;
    }

    public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate) {
        java.util.Date utilDate = null;
        if (sqlDate != null) {
            utilDate = new java.util.Date(sqlDate.getTime());
        }
        return utilDate;
    }
}
