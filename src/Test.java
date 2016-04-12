import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.house.beans.Buyer;
import com.house.beans.House;
import com.house.beans.Manager;
import com.house.beans.Notice;
import com.house.beans.Owner;
import com.house.beans.Seller;
import com.house.beans.SellerAccount;
import com.house.persistence.HouseMapper;
import com.house.service.AccountService;
import com.house.service.BuyerService;
import com.house.service.HouseService;
import com.house.service.NoticeService;
import com.house.service.SellerService;


public class Test {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		HouseService ser = context.getBean(HouseService.class);
		List<House> houses = ser.getHousesByBuyerID(26);
		for(House h:houses){
			System.out.println(h);
		}
	}
}
