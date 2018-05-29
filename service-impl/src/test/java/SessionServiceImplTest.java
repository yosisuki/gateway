import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.tiket.tix.gateway.entity.constant.unit.test.CommonTestVariable;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.entity.outbound.PrivilegeResponse;
import com.tiket.tix.gateway.libraries.utility.CommonHelper;
import com.tiket.tix.gateway.service.api.PrivilegeService;
import com.tiket.tix.gateway.service.impl.SessionServiceImpl;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CommonHelper.class)
public class SessionServiceImplTest {

  @InjectMocks
  private SessionServiceImpl sessionService;

  @Mock
  private PrivilegeService privilegeService;

  GatewayBaseResponse<String> RESPONSE;
  List<PrivilegeResponse> PRIVILEGE_RESPONSE;

  @Test
  public void getSessionData() throws Exception {
    when(this.privilegeService.getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        CommonTestVariable.PRIVILEGES)).thenReturn(Single.just(CommonTestVariable
        .PRIVILEGE_RESPONSE));

    GatewayBaseResponse<String> response = this.sessionService
        .getSessionData(CommonTestVariable.MANDATORY_REQUEST,
            CommonTestVariable.PRIVILEGES,
            "1,2,3",
            CommonTestVariable.SESSION_DATA).blockingGet();

    assertEquals(this.RESPONSE, response);
    verify(this.privilegeService).getAuthorizedPrivileges(CommonTestVariable.MANDATORY_REQUEST,
        CommonTestVariable.PRIVILEGES);


  }

  @Before
  public void setUp() throws Exception {
    initMocks(this);

    PowerMockito.mockStatic(CommonHelper.class);
    PowerMockito.when(CommonHelper.newDate())
        .thenReturn(CommonTestVariable.MOCK_DATE);
    PowerMockito.when(CommonHelper.convertStringToListInteger("1,2,3",","))
        .thenReturn(Arrays.asList(1,2,3));

    PrivilegeResponse privilegeResponse = new PrivilegeResponse();
    privilegeResponse.setPrivilegeId("320");
    privilegeResponse.setPrivilegeName("coba");

    this.PRIVILEGE_RESPONSE = new ArrayList<>();
    this.PRIVILEGE_RESPONSE.add(privilegeResponse);

    PrivilegeResponse privilegeResponse2 = new PrivilegeResponse();
    privilegeResponse2.setPrivilegeId("321");
    privilegeResponse2.setPrivilegeName("coba1");
    this.PRIVILEGE_RESPONSE.add(privilegeResponse2);

    this.RESPONSE = new GatewayBaseResponse<>();
    this.RESPONSE.setSessionData(CommonTestVariable.SESSION_DATA);
    this.RESPONSE.setPrivileges(this.PRIVILEGE_RESPONSE);
    this.RESPONSE.setRoles(CommonTestVariable.LIST_INTEGER);
    this.RESPONSE.setCode(CommonTestVariable.BASE_RESPONSE.getCode());
    this.RESPONSE.setMessage(CommonTestVariable.BASE_RESPONSE.getMessage());
    this.RESPONSE.setServerTime(CommonTestVariable.MOCK_DATE);

  }

  @After
  public void tearDown() throws Exception {
    verifyNoMoreInteractions(this.privilegeService);
  }

}
