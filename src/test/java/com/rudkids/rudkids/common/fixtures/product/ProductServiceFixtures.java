package com.rudkids.rudkids.common.fixtures.product;

import com.rudkids.rudkids.common.ServiceTest;
import com.rudkids.rudkids.domain.item.ItemStore;
import com.rudkids.rudkids.domain.item.domain.*;
import com.rudkids.rudkids.domain.product.ProductReader;
import com.rudkids.rudkids.domain.product.ProductStore;
import com.rudkids.rudkids.domain.product.domain.*;
import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.domain.user.domain.PhoneNumber;
import com.rudkids.rudkids.domain.user.domain.SocialType;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.domain.UserName;
import com.rudkids.rudkids.infrastructure.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ServiceTest
public class ProductServiceFixtures {
    @Autowired
    protected ProductService productService;

    @Autowired
    protected ProductReader productReader;

    @Autowired
    protected ProductStore productStore;

    @Autowired
    protected ItemStore itemStore;

    @Autowired
    protected UserRepository userRepository;

    protected List<Product> products;
    protected Item item;
    protected User user;

    @BeforeEach
    void inputData() {
        user = User.builder()
            .name(UserName.create("이규진"))
            .age(19)
            .email("leekuin14@gmail.com")
            .gender("MAIL")
            .phoneNumber(PhoneNumber.create("01029401509"))
            .socialType(SocialType.GOOGLE)
            .build();
        user.changeAuthorityAdmin();
        userRepository.save(user);

        products = List.of(
            Product.builder()
                .title(Title.create("프로덕트 No.1"))
                .productBio(ProductBio.create("소개드립니다~"))
                .frontImage(ProductFrontImage.create("path", "url"))
                .backImage(ProductBackImage.create("path", "url"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.2"))
                .productBio(ProductBio.create("소개드립니다~"))
                .frontImage(ProductFrontImage.create("path", "url"))
                .backImage(ProductBackImage.create("path", "url"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.3"))
                .productBio(ProductBio.create("소개드립니다~"))
                .frontImage(ProductFrontImage.create("path", "url"))
                .backImage(ProductBackImage.create("path", "url"))
                .build(),
            Product.builder()
                .title(Title.create("프로덕트 No.4"))
                .productBio(ProductBio.create("소개드립니다~"))
                .frontImage(ProductFrontImage.create("path", "url"))
                .backImage(ProductBackImage.create("path", "url"))
                .build()
        );
        products.forEach(productStore::store);


        item = Item.builder()
            .name(Name.create("No.3", "남바쓰리"))
            .price(Price.create(2_990))
            .quantity(Quantity.create(1_000))
            .itemBio(ItemBio.create("소개글입니다~"))
            .limitType(LimitType.NORMAL)
            .build();
        itemStore.store(item);
        item.setProduct(products.get(0));
    }
}
