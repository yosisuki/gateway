//@RunWith(PowerMockRunner.class)
//@PrepareForTest({MDC.class})
//public class SessionControllerTest {

//  @InjectMocks
//  private GatewayController sessionController;
//
//  @Mock
//  private SessionService sessionService;
//
//  private MockMvc mockMvc;
//
//  @Test
//  public void getSessionDataTest() throws Exception {
//    when(this.sessionService.getSessionData(
//        CommonTestVariable.MANDATORY_REQUEST,
//        StoreIdTestVariable.PRIVILEGES,
//        CommonTestVariable.ROLES,
//        CommonTestVariable.SESSION_DATA)).thenReturn(Single.just(SessionTestVariable.GATEWAY_BASE_RESPONSE));
//
//
//    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//        .get(ApiPath.SESSION)
//        .accept(MediaType.APPLICATION_JSON)
//        .contentType(MediaType.APPLICATION_JSON)
//        .header("storeId", CommonTestVariable.STORE_ID)
//        .header("username", CommonTestVariable.USERNAME)
//        .header("channelId", CommonTestVariable.CHANNEL_ID)
//        .header("serviceId", CommonTestVariable.SERVICE_ID)
//        .header("requestId", CommonTestVariable.REQUEST_ID);
//
//    MvcResult deferredResult = this.mockMvc.perform(builder).andReturn();
//
//    this.mockMvc.perform(asyncDispatch(deferredResult))
//        .andExpect(status().isOk())
//        .andReturn();
//
//    verify(this.sessionService).getSessionData(
//        CommonTestVariable.MANDATORY_REQUEST,
//        StoreIdTestVariable.PRIVILEGES,
//        CommonTestVariable.ROLES,
//        CommonTestVariable.SESSION_DATA);
//  }
//
//  @Before
//  public void setUp() throws Exception {
//    MockitoAnnotations.initMocks(this);
//
//    PowerMockito.mockStatic(MDC.class);
//    PowerMockito.when((String) MDC.get(BaseMongoFields.PRIVILEGES)).thenReturn
//        (StoreIdTestVariable.PRIVILEGES);
//    PowerMockito.when((String) MDC.get(BaseMongoFields.STORE_ID)).thenReturn
//        (CommonTestVariable.STORE_ID);
//    PowerMockito.when((String) MDC.get(BaseMongoFields.CHANNEL_ID)).thenReturn
//        (CommonTestVariable.CHANNEL_ID);
//    PowerMockito.when((String) MDC.get(BaseMongoFields.SERVICE_ID)).thenReturn
//        (CommonTestVariable.SERVICE_ID);
//    PowerMockito.when((String) MDC.get(BaseMongoFields.REQUEST_ID)).thenReturn
//        (CommonTestVariable.REQUEST_ID);
//    PowerMockito.when((String) MDC.get(BaseMongoFields.USERNAME)).thenReturn
//        (CommonTestVariable.USERNAME);
//    PowerMockito.when((String) MDC.get(BaseMongoFields.ROLES)).thenReturn
//        (CommonTestVariable.ROLES);
//
//
//    this.mockMvc = MockMvcBuilders.standaloneSetup(this.sessionController).build();
//  }
//
//  @After
//  public void tearDown() throws Exception {
//    verifyNoMoreInteractions(this.sessionService);
//  }

//}
