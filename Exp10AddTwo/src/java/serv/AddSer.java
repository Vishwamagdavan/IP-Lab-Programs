/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Vishwa
 */
@WebService(serviceName = "AddSer")
public class AddSer {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "Addition")
    public Integer Addition(@WebParam(name = "val1") Integer val1, @WebParam(name= "val2") Integer val2) {
        return val1 + val2;
    }
}
