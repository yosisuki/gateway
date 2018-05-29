import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.tiket.tix.gateway.entity.constant.unit.test.CommonTestVariable;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.libraries.configuration.CommonEndPointService;
import com.tiket.tix.gateway.outbound.impl.GatewayOutboundServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import retrofit2.Call;
import retrofit2.Response;


public class GatewayOutboundServiceImplTest {

  Map<String, String> HEADER;

  Map<String, String> QUERY;

  Object OBJECT_REQUEST;

  String URL = "http://www.coba.com";

  @InjectMocks
  GatewayOutboundServiceImpl gatewayOutboundServiceImpl;

  @Mock
  Call<GatewayBaseResponse<Object>> CALL_OBJECT;

  GatewayBaseResponse<Object> RESPONSE_OBJECT;

  @Mock
  private CommonEndPointService gatewayEndPointService;


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

  @Test
  public void forwardRequestGetAllTest() throws Exception {
    when(gatewayEndPointService.forwardRequestGetAll(URL, HEADER, QUERY)).thenReturn(CALL_OBJECT);
    when(CALL_OBJECT.execute()).thenReturn(Response.success(RESPONSE_OBJECT));

    GatewayBaseResponse<Object> objectGatewayBaseResponse = gatewayOutboundServiceImpl
        .forwardRequestGetAll(
            URL,
            HEADER,
            QUERY).blockingGet();

    assertEquals("yosia", objectGatewayBaseResponse.getData());

    verify(gatewayEndPointService).forwardRequestGetAll(URL, HEADER, QUERY);
  }

  @Test
  public void forwardRequestGetTest() throws Exception {
    when(gatewayEndPointService.forwardRequestGet(URL, HEADER)).thenReturn(CALL_OBJECT);
    when(CALL_OBJECT.execute()).thenReturn(Response.success(RESPONSE_OBJECT));

    GatewayBaseResponse<Object> objectGatewayBaseResponse = gatewayOutboundServiceImpl
        .forwardRequestGet(
            URL,
            HEADER).blockingGet();

    assertEquals("yosia", objectGatewayBaseResponse.getData());

    verify(gatewayEndPointService).forwardRequestGet(URL, HEADER);
  }

  @Test
  public void forwardRequestPostTest() throws Exception {
    when(gatewayEndPointService.forwardRequestPost(URL, HEADER, OBJECT_REQUEST)).thenReturn
        (CALL_OBJECT);
    when(CALL_OBJECT.execute()).thenReturn(Response.success(RESPONSE_OBJECT));

    GatewayBaseResponse<Object> objectGatewayBaseResponse = gatewayOutboundServiceImpl
        .forwardRequestPost(URL, HEADER, OBJECT_REQUEST).blockingGet();

    assertEquals("yosia", objectGatewayBaseResponse.getData());

    verify(gatewayEndPointService).forwardRequestPost(URL, HEADER, OBJECT_REQUEST);
  }

  @Test
  public void forwardRequestPutTest() throws Exception {
    when(gatewayEndPointService.forwardRequestPut(URL, HEADER, OBJECT_REQUEST)).thenReturn
        (CALL_OBJECT);
    when(CALL_OBJECT.execute()).thenReturn(Response.success(RESPONSE_OBJECT));

    GatewayBaseResponse<Object> objectGatewayBaseResponse = gatewayOutboundServiceImpl
        .forwardRequestPut(URL, HEADER, OBJECT_REQUEST).blockingGet();

    assertEquals("yosia", objectGatewayBaseResponse.getData());

    verify(gatewayEndPointService).forwardRequestPut(URL, HEADER, OBJECT_REQUEST);
  }

  @Test
  public void forwardRequestDeleteTest() throws Exception {
    when(gatewayEndPointService.forwardRequestDelete(URL, HEADER)).thenReturn
        (CALL_OBJECT);
    when(CALL_OBJECT.execute()).thenReturn(Response.success(RESPONSE_OBJECT));

    GatewayBaseResponse<Object> objectGatewayBaseResponse = gatewayOutboundServiceImpl
        .forwardRequestDelete(URL, HEADER).blockingGet();

    assertEquals("yosia", objectGatewayBaseResponse.getData());

    verify(gatewayEndPointService).forwardRequestDelete(URL, HEADER);
  }

  @After
  public void tearDown() throws Exception {
    verifyNoMoreInteractions(gatewayEndPointService);
  }

}
