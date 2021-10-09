/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.account;

import java.sql.SQLException;
import javax.naming.NamingException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author TanNV
 */
public class AccountDAOTesting {
    @Test
    public void checkLoginGivenTrueAgrumentWell() throws NamingException, SQLException{
        AccountDAO dao = new AccountDAO();
        boolean exepted = true;
        boolean actual = dao.checkLogin("tantieunhi@gmail", "12345");
        Assert.assertEquals(exepted, actual);
    }
}
