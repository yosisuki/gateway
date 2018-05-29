import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.tiket.tix.gateway.entity.constant.enums.RequestMethods;
import com.tiket.tix.gateway.entity.constant.unit.test.CommonTestVariable;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.entity.outbound.PrivilegeResponse;
import com.tiket.tix.gateway.entity.outbound.PrivilegeResponseBuilder;
import com.tiket.tix.gateway.outbound.api.GatewayOutboundService;
import com.tiket.tix.gateway.service.api.PrivilegeService;
import com.tiket.tix.gateway.service.impl.GatewayServiceImpl;
import io.reactivex.Single;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import retrofit2.Call;

public class GatewayServiceImplTest {

  @InjectMocks
  GatewayServiceImpl gatewayServiceImpl;

  @Mock
  private GatewayOutboundService gatewayOutboundService;

  @Mock
  private PrivilegeService privilegeService;

  @Mock
  Call<GatewayBaseResponse<Object>> CALL_OBJECT;

  GatewayBaseResponse<Object> RESPONSE_OBJECT;

  Map<String, String> HEADER;

  Map<String, String> QUERY;

  Object OBJECT_REQUEST;

  String URL = "http://www.coba.com";

  String PARAM = "123";

  String PRIVILEGE = "1";

  String PRIVILEGE_TO_CHECK = "1,2";

  String GROUP_NAME = "banks";

  List<PrivilegeResponse> PRIVILEGE_RESPONSE = Arrays.asList(
      new PrivilegeResponseBuilder()
      .withPrivilegeId("1")
      .withPrivilegeName("satu").build(),
      new PrivilegeResponseBuilder()
          .withPrivilegeId("2")
          .withPrivilegeName("dua").build()
  );

  @Test
  public void forwardRequestGetAllTest() throws Exception {
    when(gatewayOutboundService.forwardRequestGetAll(URL, HEADER, QUERY)).thenReturn(Single.just
        (RESPONSE_OBJECT));
    when(privilegeService.checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK)).thenReturn(Single.just(true));
    when(privilegeService.getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK)).thenReturn(Single.just(PRIVILEGE_RESPONSE));

    GatewayBaseResponse<Object> result = gatewayServiceImpl.forwardRequest(
        CommonTestVariable.MANDATORY_REQUEST,
        URL,
        null,
        null,
        RequestMethods.GET,
        QUERY,
        PRIVILEGE,
        GROUP_NAME,
        PRIVILEGE_TO_CHECK
        ).blockingGet();

    assertEquals("yosia", result.getData());

    verify(gatewayOutboundService).forwardRequestGetAll(URL, HEADER, QUERY);
    verify(privilegeService).checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK);
    verify(privilegeService).getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK);

  }

  @Test
  public void forwardRequestGetTest() throws Exception {
    when(gatewayOutboundService.forwardRequestGet(URL, HEADER)).thenReturn(Single.just
        (RESPONSE_OBJECT));
    when(privilegeService.checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK)).thenReturn(Single.just(true));
    when(privilegeService.getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK)).thenReturn(Single.just(PRIVILEGE_RESPONSE));

    GatewayBaseResponse<Object> result = gatewayServiceImpl.forwardRequest(
        CommonTestVariable.MANDATORY_REQUEST,
        URL,
        PARAM,
        null,
        RequestMethods.GET,
        QUERY,
        PRIVILEGE,
        GROUP_NAME,
        PRIVILEGE_TO_CHECK
    ).blockingGet();

    assertEquals("yosia", result.getData());

    verify(gatewayOutboundService).forwardRequestGet(URL, HEADER);
    verify(privilegeService).checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK);
    verify(privilegeService).getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK);
  }

  @Test
  public void forwardRequestPostTest() throws Exception {
    when(gatewayOutboundService.forwardRequestPost(URL, HEADER, OBJECT_REQUEST)).thenReturn(Single.just
        (RESPONSE_OBJECT));
    when(privilegeService.checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK)).thenReturn(Single.just(true));
    when(privilegeService.getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK)).thenReturn(Single.just(PRIVILEGE_RESPONSE));

    GatewayBaseResponse<Object> result = gatewayServiceImpl.forwardRequest(
        CommonTestVariable.MANDATORY_REQUEST,
        URL,
        null,
        OBJECT_REQUEST,
        RequestMethods.POST,
        null,
        PRIVILEGE,
        GROUP_NAME,
        PRIVILEGE_TO_CHECK
    ).blockingGet();

    assertEquals("yosia", result.getData());

    verify(gatewayOutboundService).forwardRequestPost(URL, HEADER, OBJECT_REQUEST);
    verify(privilegeService).checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK);
    verify(privilegeService).getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK);
  }

  @Test
  public void forwardRequestPutTest() throws Exception {
    when(gatewayOutboundService.forwardRequestPut(URL + "/" + PARAM, HEADER, OBJECT_REQUEST))
        .thenReturn
        (Single.just
        (RESPONSE_OBJECT));
    when(privilegeService.checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK)).thenReturn(Single.just(true));
    when(privilegeService.getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK)).thenReturn(Single.just(PRIVILEGE_RESPONSE));

    GatewayBaseResponse<Object> result = gatewayServiceImpl.forwardRequest(
        CommonTestVariable.MANDATORY_REQUEST,
        URL,
        PARAM,
        OBJECT_REQUEST,
        RequestMethods.PUT,
        null,
        PRIVILEGE,
        GROUP_NAME,
        PRIVILEGE_TO_CHECK
    ).blockingGet();

    assertEquals("yosia", result.getData());

    verify(gatewayOutboundService).forwardRequestPut(URL + "/" + PARAM, HEADER, OBJECT_REQUEST);
    verify(privilegeService).checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK);
    verify(privilegeService).getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK);
  }

  @Test
  public void forwardRequestDeleteTest() throws Exception {
    when(gatewayOutboundService.forwardRequestDelete(URL + "/" + PARAM, HEADER))
        .thenReturn
            (Single.just
                (RESPONSE_OBJECT));
    when(privilegeService.checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK)).thenReturn(Single.just(true));
    when(privilegeService.getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK)).thenReturn(Single.just(PRIVILEGE_RESPONSE));

    GatewayBaseResponse<Object> result = gatewayServiceImpl.forwardRequest(
        CommonTestVariable.MANDATORY_REQUEST,
        URL,
        PARAM,
        null,
        RequestMethods.DELETE,
        null,
        PRIVILEGE,
        GROUP_NAME,
        PRIVILEGE_TO_CHECK
    ).blockingGet();

    assertEquals("yosia", result.getData());

    verify(gatewayOutboundService).forwardRequestDelete(URL + "/" + PARAM, HEADER);
    verify(privilegeService).checkAuthorized(PRIVILEGE, PRIVILEGE_TO_CHECK);
    verify(privilegeService).getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        PRIVILEGE_TO_CHECK);
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

    OBJECT_REQUEST = new Object();
  }

  @After
  public void tearDown() throws Exception {
    verifyNoMoreInteractions(this.gatewayOutboundService);
    verifyNoMoreInteractions(this.privilegeService);
  }
}
