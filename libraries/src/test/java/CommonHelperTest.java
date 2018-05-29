import static org.junit.Assert.assertEquals;

import com.tiket.tix.gateway.entity.constant.unit.test.CommonTestVariable;
import com.tiket.tix.gateway.libraries.utility.CommonHelper;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.data.domain.Sort.Direction;

public class CommonHelperTest {

  @InjectMocks
  CommonHelper commonHelper;

  @Test
  public void convertToSortDirectionDescTest() throws Exception {
    Direction direction = CommonHelper.convertToSortDirection("desc");
    assertEquals(direction.DESC, direction);
  }

  @Test
  public void convertToSortDirectionDescendingTest() throws Exception {
    Direction direction = CommonHelper.convertToSortDirection("descending");
    assertEquals(direction.DESC, direction);
  }

  @Test
  public void convertToSortDirectionAscTest() throws Exception {
    Direction direction = CommonHelper.convertToSortDirection("asc");
    assertEquals(direction.ASC, direction);
  }

  @Test
  public void convertToSortDirectionAscendingTest() throws Exception {
    Direction direction = CommonHelper.convertToSortDirection("ascending");
    assertEquals(direction.ASC, direction);
  }

  @Test
  public void convertMandatoryRequestToMapTest() throws Exception {
    Map<String, String> mandatoryRequestToMap = CommonHelper.convertMandatoryRequestToMap
        (CommonTestVariable.MANDATORY_REQUEST);
    assertEquals(CommonTestVariable.STORE_ID, mandatoryRequestToMap.get("storeId"));
  }

  @Test
  public void newDateTest() throws Exception {
    Date newDate = CommonHelper.newDate();
    Date DateNow = new Date();
    assertEquals(DateNow, newDate);
  }

  @Test
  public void convertStringToListIntegerTest() throws Exception {
    List<Integer> listInteger = CommonHelper.convertStringToListInteger("1,2,3", ",");
    assertEquals(CommonTestVariable.LIST_INTEGER, listInteger);
  }

  @Test
  public void convertStringToListIntegerNullTest() throws Exception {
    List<Integer> listInteger = CommonHelper.convertStringToListInteger(null, ",");
    assertEquals(Arrays.asList(), listInteger);
  }
}
