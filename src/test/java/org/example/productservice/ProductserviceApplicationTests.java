package org.example.productservice;

import org.example.productservice.models.ProductTitleAndDesc;
import org.example.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class ProductserviceApplicationTests {

	Logger log = Logger.getLogger(ProductserviceApplicationTests.class.getName());
	@Autowired
	@Qualifier("SelfProductService")
	ProductService productService;

	@Test
	void contextLoads() {
	}

	@Test
	void getProductTitleAndDesc()
	{
		Long id =102L;
		ProductTitleAndDesc productTitleAndDesc = productService.getProductTitleandDescription(id);
		String a = productTitleAndDesc.getTitle();
		String b = productTitleAndDesc.getDescription();
		log.info("Title is : "+a +"Description is : "+b);
		return;
	}

}
