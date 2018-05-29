import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tiket.tix.common.libraries.JSONHelper;
import com.tiket.tix.gateway.entity.constant.ApiPath;
import com.tiket.tix.gateway.entity.constant.enums.RequestMethods;
import com.tiket.tix.gateway.entity.constant.fields.BaseMongoFields;
import com.tiket.tix.gateway.entity.constant.unit.test.CommonTestVariable;
import com.tiket.tix.gateway.entity.dao.GatewayEndPoint;
import com.tiket.tix.gateway.entity.dao.GatewayEndPointBuilder;
import com.tiket.tix.gateway.entity.dao.Privilege;
import com.tiket.tix.gateway.entity.dao.PrivilegeBuilder;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.rest.web.controller.GatewayController;
import com.tiket.tix.gateway.service.api.GatewayEndPointService;
import com.tiket.tix.gateway.service.api.GatewayService;
import io.reactivex.Single;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.MDC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import retrofit2.Call;


@RunWith(PowerMockRunner.class)
@PrepareForTest({MDC.class})
public class GatewayControllerTest {

  @InjectMocks
  GatewayController gatewayController;

  @Mock
  GatewayService gatewayService;

  @Mock
  GatewayEndPointService gatewayEndPointService;

  @Mock
  Call<GatewayBaseResponse<Object>> CALL_OBJECT;

  private MockMvc mockMvc;

  GatewayBaseResponse<Object> RESPONSE_OBJECT;

  Map<String, String> HEADER;

  Map<String, String> QUERY;

  Privilege OBJECT_REQUEST = new PrivilegeBuilder().withPrivilegeId("123")
      .withPrivilegeDescription("123").withPrivilegeName("123").build();

  String OBJECT_REQUEST_JSON = "";

  String URL = "http://www.coba.com";

  String PARAM = "123";

  String SLUG = "banks";

  String PRIVILEGE = "1";

  String PRIVILEGE_TO_CHECK = "1,2";

  String GROUP_NAME = "banks";

  LinkedHashMap<String, String> OBJECT_MAP = new LinkedHashMap<>();

  GatewayEndPoint GATEWAY_END_POINT = new GatewayEndPointBuilder().withSlug(SLUG).withUrl(URL)
      .withPrivilegeId(PRIVILEGE).build();

  @Test
  public void receiveAndForwardGetAllTest() throws Exception {
    when(this.gatewayService.forwardRequest(CommonTestVariable.MANDATORY_REQUEST, URL, null,
        null, RequestMethods.GET, QUERY, PRIVILEGE, GROUP_NAME, PRIVILEGE_TO_CHECK))
        .thenReturn(Single
        .just
        (RESPONSE_OBJECT));

    when(gatewayEndPointService.findEndpointBySlug(CommonTestVariable.MANDATORY_REQUEST, SLUG,
        "getAll"))
        .thenReturn(GATEWAY_END_POINT);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .get(ApiPath.BASE_PATH + "/{endPoint}", "banks")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .header("storeId", CommonTestVariable.STORE_ID)
        .header("username", CommonTestVariable.USERNAME)
        .header("channelId", CommonTestVariable.CHANNEL_ID)
        .header("serviceId", CommonTestVariable.SERVICE_ID)
        .header("requestId", CommonTestVariable.REQUEST_ID)
        .param("page", "0")
        .param("size", "10");

    MvcResult deferredResult = this.mockMvc.perform(builder).andReturn();

    this.mockMvc.perform(asyncDispatch(deferredResult))
        .andExpect(status().isOk())
        .andReturn();

    verify(this.gatewayService).forwardRequest(CommonTestVariable.MANDATORY_REQUEST, URL, null,
        null, RequestMethods.GET, QUERY, PRIVILEGE, GROUP_NAME, PRIVILEGE_TO_CHECK);
  }

  @Test
  public void receiveForwardGetTest() throws Exception {
    when(this.gatewayService.forwardRequest(
        CommonTestVariable.MANDATORY_REQUEST, URL, "123",
        null, RequestMethods.GET, null, PRIVILEGE, GROUP_NAME,  PRIVILEGE_TO_CHECK)
    ).thenReturn(Single.just(RESPONSE_OBJECT));

    when(gatewayEndPointService.findEndpointBySlug(
        CommonTestVariable.MANDATORY_REQUEST, SLUG,
        "get"))
        .thenReturn(GATEWAY_END_POINT);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .get(ApiPath.BASE_PATH + "/{endPoint}/{param}" , "banks", "123")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .header("storeId", CommonTestVariable.STORE_ID)
        .header("username", CommonTestVariable.USERNAME)
        .header("channelId", CommonTestVariable.CHANNEL_ID)
        .header("serviceId", CommonTestVariable.SERVICE_ID)
        .header("requestId", CommonTestVariable.REQUEST_ID);

    MvcResult deferredResult = this.mockMvc.perform(builder).andReturn();

    this.mockMvc.perform(asyncDispatch(deferredResult))
        .andExpect(status().isOk())
        .andReturn();

    verify(this.gatewayService).forwardRequest(CommonTestVariable.MANDATORY_REQUEST, URL, "123",
        null, RequestMethods.GET, null, PRIVILEGE, GROUP_NAME, PRIVILEGE_TO_CHECK);
  }

  @Test
  public void receiveForwardDeleteTest() throws Exception {
    when(this.gatewayService.forwardRequest(
        CommonTestVariable.MANDATORY_REQUEST, URL, "123",
        null, RequestMethods.DELETE, null, PRIVILEGE, GROUP_NAME, PRIVILEGE_TO_CHECK)
    ).thenReturn(Single.just(RESPONSE_OBJECT));

    when(gatewayEndPointService.findEndpointBySlug(
        CommonTestVariable.MANDATORY_REQUEST, SLUG,
        "delete"))
        .thenReturn(GATEWAY_END_POINT);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .delete(ApiPath.BASE_PATH + "/{endPoint}/{param}" , "banks", "123")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .header("storeId", CommonTestVariable.STORE_ID)
        .header("username", CommonTestVariable.USERNAME)
        .header("channelId", CommonTestVariable.CHANNEL_ID)
        .header("serviceId", CommonTestVariable.SERVICE_ID)
        .header("requestId", CommonTestVariable.REQUEST_ID);

    MvcResult deferredResult = this.mockMvc.perform(builder).andReturn();

    this.mockMvc.perform(asyncDispatch(deferredResult))
        .andExpect(status().isOk())
        .andReturn();

    verify(this.gatewayService).forwardRequest(CommonTestVariable.MANDATORY_REQUEST, URL, "123",
        null, RequestMethods.DELETE, null, PRIVILEGE, GROUP_NAME, PRIVILEGE_TO_CHECK);
  }

  @Before
  public void setUp() throws Exception {
    initMocks(this);

    HEADER = new HashMap<>();
    HEADER.put("storeId", CommonTestVariable.STORE_ID);
    HEADER.put("channelId", CommonTestVariable.CHANNEL_ID);
    HEADER.put("requestId", CommonTestVariable.REQUEST_ID);
    HEADER.put("serviceId", CommonTestVariable.SERVICE_ID);
    HEADER.put("username", CommonTestVariable.USERNAME);

    QUERY = new HashMap<>();
    QUERY.put("page", "0");
    QUERY.put("size", "10");

    RESPONSE_OBJECT = new GatewayBaseResponse<>();
    RESPONSE_OBJECT.setCode("SUCCESS");
    RESPONSE_OBJECT.setData("yosia");

    PowerMockito.mockStatic(MDC.class);
    PowerMockito.when((String) MDC.get(BaseMongoFields.PRIVILEGES)).thenReturn
        (PRIVILEGE_TO_CHECK);
    PowerMockito.when((String) MDC.get(BaseMongoFields.STORE_ID)).thenReturn
        (CommonTestVariable.STORE_ID);
    PowerMockito.when((String) MDC.get(BaseMongoFields.CHANNEL_ID)).thenReturn
        (CommonTestVariable.CHANNEL_ID);
    PowerMockito.when((String) MDC.get(BaseMongoFields.SERVICE_ID)).thenReturn
        (CommonTestVariable.SERVICE_ID);
    PowerMockito.when((String) MDC.get(BaseMongoFields.REQUEST_ID)).thenReturn
        (CommonTestVariable.REQUEST_ID);
    PowerMockito.when((String) MDC.get(BaseMongoFields.USERNAME)).thenReturn
        (CommonTestVariable.USERNAME);

    OBJECT_REQUEST_JSON = "{\"privilegeId\" : \"123\", \"privilegeName\" : \"123\", "
        + "\"privilegeDescription\" : \"123\" }";

    OBJECT_MAP.put("privilegeId", "123");
    OBJECT_MAP.put("privilegeName", "123");
    OBJECT_MAP.put("privilegeDescription", "123");

    this.mockMvc = MockMvcBuilders.standaloneSetup(this.gatewayController).build();
  }

  @After
  public void tearDown() throws Exception {
    verifyNoMoreInteractions(this.gatewayService);
  }
}
