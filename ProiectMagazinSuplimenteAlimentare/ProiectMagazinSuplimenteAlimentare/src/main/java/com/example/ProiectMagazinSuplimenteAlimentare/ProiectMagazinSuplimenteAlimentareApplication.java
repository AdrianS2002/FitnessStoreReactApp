package com.example.ProiectMagazinSuplimenteAlimentare;

import com.example.ProiectMagazinSuplimenteAlimentare.dto.*;
import com.example.ProiectMagazinSuplimenteAlimentare.exceptions.ApiExceptionResponse;
import com.example.ProiectMagazinSuplimenteAlimentare.exporter.XMLFileExporter;
import com.example.ProiectMagazinSuplimenteAlimentare.mapper.*;
import com.example.ProiectMagazinSuplimenteAlimentare.model.*;
import com.example.ProiectMagazinSuplimenteAlimentare.service.EmailCronjobService;
import com.example.ProiectMagazinSuplimenteAlimentare.service.comment.CommentService;
import com.example.ProiectMagazinSuplimenteAlimentare.service.order.OrderService;
import com.example.ProiectMagazinSuplimenteAlimentare.service.product.ProductService;
import com.example.ProiectMagazinSuplimenteAlimentare.service.productcategory.ProductCategoryService;
import com.example.ProiectMagazinSuplimenteAlimentare.service.role.RoleService;
import com.example.ProiectMagazinSuplimenteAlimentare.service.tag.ProductTagService;
import com.example.ProiectMagazinSuplimenteAlimentare.service.user.UserService;
import jakarta.activation.DataHandler;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;
@ComponentScan(basePackages = "com.example.ProiectMagazinSuplimenteAlimentare")
@SpringBootApplication
@EnableScheduling
public class ProiectMagazinSuplimenteAlimentareApplication {

	private EmailCronjobService emailCronjobService;





	public static void main(String[] args) {
		SpringApplication.run(ProiectMagazinSuplimenteAlimentareApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProductService productService, ProductCategoryService productCategoryService, ProductTagService productTagService, CommentService commentService, RoleService roleService, OrderService orderService, UserService userService) {
		return args -> {

//
//			ProductCategory productCategory1 = new ProductCategory();
//			productCategory1.setName("Proteine");
//			productCategoryService.save(productCategory1);
//			ProductCategory productCategory2 = new ProductCategory();
//			productCategory2.setName("Vitamine");
//			productCategoryService.save(productCategory2);
//
//			ProductCategory productCategory3 = new ProductCategory();
//			productCategory3.setName("Steroizi");
//			productCategoryService.save(productCategory3);
//			productCategoryService.deleteProductCategory(productCategory3.getId());
//
//			productCategory2.setName("Vitamine si minerale");
//			productCategoryService.updateProductCategory(productCategory2);
//			System.out.println(productCategoryService.findAll());
//			System.out.println(productCategoryService.findProductCategoryById(productCategory1.getId()));
//
//			ProductTag productTag1 = new ProductTag();
//			productTag1.setName("Vegan");
//			productTagService.saveProductTag(productTag1);
//
//
//			ProductTag productTag2 = new ProductTag();
//			productTag2.setName("Diet");
//			productTagService.saveProductTag(productTag2);
//
//			ProductTag productTag3 = new ProductTag();
//			productTag3.setName("Carbs");
//			productTagService.saveProductTag(productTag3);
//
//			productTagService.deleteProductTag(productTag3.getId());
//
//			System.out.println(productTagService.findProductTagById(productTag2.getId()).getDisplayName());
//			System.out.println(productTagService.findProductTagByName("Diet").getName());
//
			Product product1 = new Product();
			product1.setName("Whey");
			product1.setPrice(100.0);
			product1.setQuantity(10);
			product1.setDescription("Proteine de calitate");
			product1.setImage("https://i.postimg.cc/FKRvmh6P/image.png");
			ProductCreationDTO productCreationDTO = ProductMapper.toCreationDTO(product1);
			product1 = ProductMapper.toEntity(productService.saveProduct(productCreationDTO));
			System.out.println(productService.findAll());

			Product product2 = new Product();
			product2.setName("Vitamine");
			product2.setPrice(100.0);
			product2.setQuantity(10);
			product2.setDescription("Vitamine de calitate");
			product2.setImage("https://i.postimg.cc/KYH28dSm/image.png");
			ProductCreationDTO productCreationDTO1 = ProductMapper.toCreationDTO(product2);
			product2 = ProductMapper.toEntity(productService.saveProduct(productCreationDTO1));
			System.out.println(productService.findAll());

			ProductCategory productCategory1 = new ProductCategory();
			productCategory1.setName("Supplements");
			ProductCategoryCreationDTO productCategoryCreationDTO = ProductCategoryMapper.toCreationDto(productCategory1);
			productCategory1 = ProductCategoryMapper.toEntity(productCategoryService.save(productCategoryCreationDTO));

			Comment comment = new Comment();
			comment.setText("PRODUS");
			comment.setDate(java.time.LocalDateTime.now());
			CommentCreationDTO commentCreationDTO = CommentMapper.toCreationDto(comment);
			comment = CommentMapper.toEntity(commentService.saveComment(commentCreationDTO));

			CommentToProductAtribution commentToProductAtributionDTO = new CommentToProductAtribution();
			commentToProductAtributionDTO.setCommentId(comment.getId());
			commentToProductAtributionDTO.setProductId(product1.getId());
			commentService.assignProduct(commentToProductAtributionDTO);






			ProductCategory productCategory2 = new ProductCategory();
			productCategory2.setName("Snacks");
			ProductCategoryCreationDTO productCategoryCreationDTO1 = ProductCategoryMapper.toCreationDto(productCategory2);
			productCategory2 = ProductCategoryMapper.toEntity(productCategoryService.save(productCategoryCreationDTO1));
			System.out.println(productCategoryService.findAll());


			CategoryAtributionDTO categoryAtributionDTO = new CategoryAtributionDTO();
			categoryAtributionDTO.setProductId(product1.getId());
			categoryAtributionDTO.setProductCategoryId(productCategory1.getId());
			productCategoryService.assignProduct(categoryAtributionDTO);
			categoryAtributionDTO.setProductId(product2.getId());
			categoryAtributionDTO.setProductCategoryId(productCategory1.getId());
			productCategoryService.assignProduct(categoryAtributionDTO);
			//System.out.println(productCategoryService.findAll());

			categoryAtributionDTO.setProductId(product1.getId());
			categoryAtributionDTO.setProductCategoryId(productCategory2.getId());
			productCategoryService.assignProduct(categoryAtributionDTO);
			System.out.println(productCategoryService.findAll());

			ProductTag productTag1 = new ProductTag();
			productTag1.setName("Vegan");
			TagCreationDTO tagCreationDTO = ProductTagMapper.toCreationDto(productTag1);
			productTag1 = ProductTagMapper.toEntity(productTagService.saveProductTag(tagCreationDTO));

			TagAtributionDTO tagAtributionDTO = new TagAtributionDTO();
			tagAtributionDTO.setProductId(product1.getId());
			tagAtributionDTO.setProductTagId(productTag1.getId());
			productTagService.assignProduct(tagAtributionDTO);





//
//
//			CategoryAtributionDTO dto = new CategoryAtributionDTO();
//			product1.setDescription("Proteine de calitate");
//			product1.setImage("https://www.google.com");
//			productService.saveProduct(product1);
//			TagAtributionDTO dtoTag = new TagAtributionDTO();
//			dtoTag.setProductId(product1.getId());
//			dtoTag.setProductTagId(productTag1.getId());
//			productTagService.assignProduct(dtoTag);
//			System.out.println(productService.findProductById(product1.getId()).getName());
//			dto.setProductId(product1.getId());
//			dto.setProductCategoryId(productCategory1.getId());
//			productCategoryService.assignProduct(dto);
//			System.out.println(productCategoryService.findProductCategoryByName("Proteine"));
//			System.out.println(productTagService.findProductTagByName("Vegan"));
//			//deleteProductTag(productTag1.getId());
//			//productCategoryService.deleteProductCategory(productCategory1.getId());
//
//			Product product2 = new Product();
//			product2.setName("Vitamine");
//			product2.setPrice(30.0);
//			product2.setQuantity(130);
//			product2.setDescription("Vitamine de calitate");
//			product2.setImage("https://www.google.com");
//			productService.saveProduct(product2);
//			dtoTag.setProductId(product2.getId());
//			dtoTag.setProductTagId(productTag2.getId());
//			productTagService.assignProduct(dtoTag);
//			dto.setProductId(product2.getId());
//			dto.setProductCategoryId(productCategory1.getId());
//			productCategoryService.assignProduct(dto);
//
//			System.out.println("CANTITATEA " +productService.findProductById(product2.getId()).getQuantity());
//			product2.setQuantity(100);
//			productService.updateProduct(product2);
//			System.out.println("CANTITATEA " +productService.findProductById(product2.getId()).getQuantity());
//
//			Product product3 = new Product();
//			product3.setName("Stero");
//			product3.setPrice(30.0);
//			product3.setQuantity(130);
//
//
//			product3.setDescription("Vitamine de calitate");
//			product3.setImage("https://www.google.com");
//			productService.saveProduct(product3);
//			productService.deleteProduct(product3.getId());
//			//productTagService.deleteProductTag(productTag2.getId());
//
//			System.out.println(productService.findAll().toString());
//
//
//			Order order = new Order();
//			order.setOrderNumber(123L);
//			orderService.addProduct(order,product1);
//			orderService.saveOrder(order);
//			System.out.println(orderService.getTotalPrice(order));
//


			Order order = new Order();
			//order.setId(1L);
			order.setOrderNumber(123L);
			OrderCreationDTO orderCreationDTO = OrderMapper.toCreationDto(order);
			order = OrderMapper.toEntity(orderService.saveOrder(orderCreationDTO));

			Order order1 = new Order();
			order1.setOrderNumber(124L);
			OrderCreationDTO orderCreationDTO1 = OrderMapper.toCreationDto(order1);
			order1 = OrderMapper.toEntity(orderService.saveOrder(orderCreationDTO1));


			/*OrderProductDTO orderProductDTO = new OrderProductDTO();
			orderProductDTO.setOrderId(order.getId());
			orderProductDTO.setProductId(product1.getId());
			orderService.assignProduct(orderProductDTO);*/

//			orderProductDTO.setOrderId(order.getId());
//			orderProductDTO.setProductId(product2.getId());
//			orderService.assignProduct(orderProductDTO);
//			System.out.println(orderService.findAll());

			/*orderProductDTO.setOrderId(order1.getId());
			orderProductDTO.setProductId(product1.getId());
			orderService.assignProduct(orderProductDTO);*/

			Role role = new Role();
			role.setRole("ADMIN");
			RoleCreationDTO roleCreationDTO = RoleMapper.toCreationDTO(role);
			role = RoleMapper.toEntity(roleService.saveRole(roleCreationDTO));

			Role role1 = new Role();
			role1.setRole("USER");
			RoleCreationDTO roleCreationDTO1 = RoleMapper.toCreationDTO(role1);
			role1 = RoleMapper.toEntity(roleService.saveRole(roleCreationDTO1));

			System.out.println(roleService.findAll());

//
//
//
			User user1 = new User();
			user1.setName("Userone");
			user1.setUsername("aschiop2002@yahoo.com");
			user1.setPassword("Password1");
			user1.setTelephone("0772165094");
			UserCreationDTO userCreationDTO = UserMapper.toCreationDto(user1);
			user1 = UserMapper.toEntity(userService.saveUser(userCreationDTO));


			CommentToUserAtributtionDTO commentToUserAtributtionDTO = new CommentToUserAtributtionDTO();
			commentToUserAtributtionDTO.setUserId(user1.getId());
			commentToUserAtributtionDTO.setCommentId(comment.getId());
			commentService.assignUser(commentToUserAtributtionDTO);

			User user2 = new User();
			user2.setName("Userdoi");
			user2.setUsername("aschiop02@gmail.com");
			user2.setPassword("Password1");
			user2.setTelephone("0772165094");
			UserCreationDTO userCreationDTO1 = UserMapper.toCreationDto(user2);
			user2 = UserMapper.toEntity(userService.saveUser(userCreationDTO1));
			System.out.println(userService.findAll());
			user2.setPassword("Password2");
			userService.updateUser(UserMapper.toDto(user2));
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			System.out.println(userService.findUserById(user2.getId()).getPassword());


			RoleUserAtributtionDTO roleUserAtributtionDTO = new RoleUserAtributtionDTO();
			roleUserAtributtionDTO.setUserId(user1.getId());
			roleUserAtributtionDTO.setRoleId(role.getId());
			userService.assignRole(roleUserAtributtionDTO);

			RoleUserAtributtionDTO roleUserAtributtionDTO1 = new RoleUserAtributtionDTO();
			roleUserAtributtionDTO1.setUserId(user2.getId());
			roleUserAtributtionDTO1.setRoleId(role1.getId());
			userService.assignRole(roleUserAtributtionDTO1);




			System.out.println(commentService.findAll());

			OrderUserDTO orderUserDTO = new OrderUserDTO();
			orderUserDTO.setOrderId(order.getId());
			orderUserDTO.setUserId(user1.getId());
			userService.assignOrder(orderUserDTO);


			orderUserDTO.setOrderId(order1.getId());
			orderUserDTO.setUserId(user1.getId());
			userService.assignOrder(orderUserDTO);

			System.out.println(userService.findAll());
//			User user2 = new User();
//			user2.setName("User2");
//			user2.setUsername("username2");
//			user2.setPassword("password2");
//			user2.setRoles(new ArrayList<>(List.of(role1)));
//			userService.saveUser(user2);
//
//
//			System.out.println(userService.findUserById(user1.getId()).getName());
//			System.out.println(userService.findUserByUsername("username2").getName());
//			System.out.println(userService.findAll().toString());
//
//			AuthDTO authDTO = new AuthDTO();
//			authDTO.setUsername("usernam");
//			authDTO.setPassword("password1");
//			try{
//				User loggedInUser = userService.login(authDTO);
//				if(loggedInUser != null){
//					System.out.println("User logged in: " + loggedInUser.getUsername());
//					System.out.println("User roles: " + loggedInUser.getRoles());
//				}
//				else {
//					System.out.println("User not logged in");
//				}
//			} catch (ApiExceptionResponse e){
//				System.out.println(e.getMessage());
//				for (String error : e.getErrors()){
//					System.out.println(error);
//				}
//			}
//			OrderProductDTO orderProductDTO = new OrderProductDTO();
//			orderProductDTO	.setOrderId(order.getId());
//			orderProductDTO.setProductId(product1.getId());
//
//			orderService.assignProduct(orderProductDTO);
//			orderProductDTO	.setOrderId(order.getId());
//			orderProductDTO.setProductId(product2.getId());
//			orderService.assignProduct(orderProductDTO);
//
//			System.out.println(orderService.findAll());
//
//
			List<Product> products = productService.findAll();
			XMLFileExporter exporter = new XMLFileExporter();
			String xmlOutput = exporter.exportData(products);
			System.out.println(xmlOutput);

			System.out.println(userService.findAll());
		};



	}
}
