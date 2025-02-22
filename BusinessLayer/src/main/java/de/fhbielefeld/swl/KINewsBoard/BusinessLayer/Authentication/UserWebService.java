
package de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserWebService", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserWebService {


    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "configureWebService", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.ConfigureWebService")
    @ResponseWrapper(localName = "configureWebServiceResponse", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.ConfigureWebServiceResponse")
    @Action(input = "http://webservices.user.scl.fhbielefeld.de/UserWebService/configureWebServiceRequest", output = "http://webservices.user.scl.fhbielefeld.de/UserWebService/configureWebServiceResponse")
    public void configureWebService();

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "userHasRight", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.UserHasRight")
    @ResponseWrapper(localName = "userHasRightResponse", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.UserHasRightResponse")
    @Action(input = "http://webservices.user.scl.fhbielefeld.de/UserWebService/userHasRightRequest", output = "http://webservices.user.scl.fhbielefeld.de/UserWebService/userHasRightResponse")
    public String userHasRight(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "grantRight", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.GrantRight")
    @ResponseWrapper(localName = "grantRightResponse", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.GrantRightResponse")
    @Action(input = "http://webservices.user.scl.fhbielefeld.de/UserWebService/grantRightRequest", output = "http://webservices.user.scl.fhbielefeld.de/UserWebService/grantRightResponse")
    public String grantRight(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "revokeRight", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.RevokeRight")
    @ResponseWrapper(localName = "revokeRightResponse", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.RevokeRightResponse")
    @Action(input = "http://webservices.user.scl.fhbielefeld.de/UserWebService/revokeRightRequest", output = "http://webservices.user.scl.fhbielefeld.de/UserWebService/revokeRightResponse")
    public String revokeRight(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createUser", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.CreateUserResponse")
    @Action(input = "http://webservices.user.scl.fhbielefeld.de/UserWebService/createUserRequest", output = "http://webservices.user.scl.fhbielefeld.de/UserWebService/createUserResponse")
    public String createUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "saveUser", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.SaveUser")
    @ResponseWrapper(localName = "saveUserResponse", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.SaveUserResponse")
    @Action(input = "http://webservices.user.scl.fhbielefeld.de/UserWebService/saveUserRequest", output = "http://webservices.user.scl.fhbielefeld.de/UserWebService/saveUserResponse")
    public String saveUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param password
     * @param username
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "loginUser", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.LoginUser")
    @ResponseWrapper(localName = "loginUserResponse", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.LoginUserResponse")
    @Action(input = "http://webservices.user.scl.fhbielefeld.de/UserWebService/loginUserRequest", output = "http://webservices.user.scl.fhbielefeld.de/UserWebService/loginUserResponse")
    public String loginUser(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "performLogout", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.PerformLogout")
    @ResponseWrapper(localName = "performLogoutResponse", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.PerformLogoutResponse")
    @Action(input = "http://webservices.user.scl.fhbielefeld.de/UserWebService/performLogoutRequest", output = "http://webservices.user.scl.fhbielefeld.de/UserWebService/performLogoutResponse")
    public String performLogout(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUser", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.GetUser")
    @ResponseWrapper(localName = "getUserResponse", targetNamespace = "http://webservices.user.scl.fhbielefeld.de/", className = "de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication.GetUserResponse")
    @Action(input = "http://webservices.user.scl.fhbielefeld.de/UserWebService/getUserRequest", output = "http://webservices.user.scl.fhbielefeld.de/UserWebService/getUserResponse")
    public String getUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

}
