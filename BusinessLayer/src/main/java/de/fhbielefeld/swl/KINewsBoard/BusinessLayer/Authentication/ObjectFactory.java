
package de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PerformLogout_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "performLogout");
    private final static QName _RevokeRight_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "revokeRight");
    private final static QName _PerformLogoutResponse_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "performLogoutResponse");
    private final static QName _LoginUser_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "loginUser");
    private final static QName _User_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "user");
    private final static QName _ConfigureWebService_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "configureWebService");
    private final static QName _SaveUser_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "saveUser");
    private final static QName _CreateUser_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "createUser");
    private final static QName _SaveUserResponse_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "saveUserResponse");
    private final static QName _CreateUserResponse_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "createUserResponse");
    private final static QName _GrantRightResponse_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "grantRightResponse");
    private final static QName _LoginUserResponse_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "loginUserResponse");
    private final static QName _GetUser_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "getUser");
    private final static QName _RevokeRightResponse_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "revokeRightResponse");
    private final static QName _GetUserResponse_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "getUserResponse");
    private final static QName _GrantRight_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "grantRight");
    private final static QName _UserHasRightResponse_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "userHasRightResponse");
    private final static QName _UserHasRight_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "userHasRight");
    private final static QName _ConfigureWebServiceResponse_QNAME = new QName("http://webservices.user.scl.fhbielefeld.de/", "configureWebServiceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.fhbielefeld.swl.KINewsBoard.BusinessLayer.Authentication
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link GrantRightResponse }
     * 
     */
    public GrantRightResponse createGrantRightResponse() {
        return new GrantRightResponse();
    }

    /**
     * Create an instance of {@link LoginUserResponse }
     * 
     */
    public LoginUserResponse createLoginUserResponse() {
        return new LoginUserResponse();
    }

    /**
     * Create an instance of {@link RevokeRightResponse }
     * 
     */
    public RevokeRightResponse createRevokeRightResponse() {
        return new RevokeRightResponse();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link ConfigureWebServiceResponse }
     * 
     */
    public ConfigureWebServiceResponse createConfigureWebServiceResponse() {
        return new ConfigureWebServiceResponse();
    }

    /**
     * Create an instance of {@link GrantRight }
     * 
     */
    public GrantRight createGrantRight() {
        return new GrantRight();
    }

    /**
     * Create an instance of {@link UserHasRight }
     * 
     */
    public UserHasRight createUserHasRight() {
        return new UserHasRight();
    }

    /**
     * Create an instance of {@link UserHasRightResponse }
     * 
     */
    public UserHasRightResponse createUserHasRightResponse() {
        return new UserHasRightResponse();
    }

    /**
     * Create an instance of {@link PerformLogoutResponse }
     * 
     */
    public PerformLogoutResponse createPerformLogoutResponse() {
        return new PerformLogoutResponse();
    }

    /**
     * Create an instance of {@link PerformLogout }
     * 
     */
    public PerformLogout createPerformLogout() {
        return new PerformLogout();
    }

    /**
     * Create an instance of {@link RevokeRight }
     * 
     */
    public RevokeRight createRevokeRight() {
        return new RevokeRight();
    }

    /**
     * Create an instance of {@link LoginUser }
     * 
     */
    public LoginUser createLoginUser() {
        return new LoginUser();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link SaveUserResponse }
     * 
     */
    public SaveUserResponse createSaveUserResponse() {
        return new SaveUserResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link ConfigureWebService }
     * 
     */
    public ConfigureWebService createConfigureWebService() {
        return new ConfigureWebService();
    }

    /**
     * Create an instance of {@link SaveUser }
     * 
     */
    public SaveUser createSaveUser() {
        return new SaveUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformLogout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "performLogout")
    public JAXBElement<PerformLogout> createPerformLogout(PerformLogout value) {
        return new JAXBElement<PerformLogout>(_PerformLogout_QNAME, PerformLogout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevokeRight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "revokeRight")
    public JAXBElement<RevokeRight> createRevokeRight(RevokeRight value) {
        return new JAXBElement<RevokeRight>(_RevokeRight_QNAME, RevokeRight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformLogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "performLogoutResponse")
    public JAXBElement<PerformLogoutResponse> createPerformLogoutResponse(PerformLogoutResponse value) {
        return new JAXBElement<PerformLogoutResponse>(_PerformLogoutResponse_QNAME, PerformLogoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "loginUser")
    public JAXBElement<LoginUser> createLoginUser(LoginUser value) {
        return new JAXBElement<LoginUser>(_LoginUser_QNAME, LoginUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureWebService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "configureWebService")
    public JAXBElement<ConfigureWebService> createConfigureWebService(ConfigureWebService value) {
        return new JAXBElement<ConfigureWebService>(_ConfigureWebService_QNAME, ConfigureWebService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "saveUser")
    public JAXBElement<SaveUser> createSaveUser(SaveUser value) {
        return new JAXBElement<SaveUser>(_SaveUser_QNAME, SaveUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "saveUserResponse")
    public JAXBElement<SaveUserResponse> createSaveUserResponse(SaveUserResponse value) {
        return new JAXBElement<SaveUserResponse>(_SaveUserResponse_QNAME, SaveUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantRightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "grantRightResponse")
    public JAXBElement<GrantRightResponse> createGrantRightResponse(GrantRightResponse value) {
        return new JAXBElement<GrantRightResponse>(_GrantRightResponse_QNAME, GrantRightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "loginUserResponse")
    public JAXBElement<LoginUserResponse> createLoginUserResponse(LoginUserResponse value) {
        return new JAXBElement<LoginUserResponse>(_LoginUserResponse_QNAME, LoginUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevokeRightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "revokeRightResponse")
    public JAXBElement<RevokeRightResponse> createRevokeRightResponse(RevokeRightResponse value) {
        return new JAXBElement<RevokeRightResponse>(_RevokeRightResponse_QNAME, RevokeRightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrantRight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "grantRight")
    public JAXBElement<GrantRight> createGrantRight(GrantRight value) {
        return new JAXBElement<GrantRight>(_GrantRight_QNAME, GrantRight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserHasRightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "userHasRightResponse")
    public JAXBElement<UserHasRightResponse> createUserHasRightResponse(UserHasRightResponse value) {
        return new JAXBElement<UserHasRightResponse>(_UserHasRightResponse_QNAME, UserHasRightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserHasRight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "userHasRight")
    public JAXBElement<UserHasRight> createUserHasRight(UserHasRight value) {
        return new JAXBElement<UserHasRight>(_UserHasRight_QNAME, UserHasRight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureWebServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.user.scl.fhbielefeld.de/", name = "configureWebServiceResponse")
    public JAXBElement<ConfigureWebServiceResponse> createConfigureWebServiceResponse(ConfigureWebServiceResponse value) {
        return new JAXBElement<ConfigureWebServiceResponse>(_ConfigureWebServiceResponse_QNAME, ConfigureWebServiceResponse.class, null, value);
    }

}
