package ru.egorov.ProfitSeeking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.egorov.ProfitSeeking.dto.ProductDto;
import ru.egorov.ProfitSeeking.model.Item;
import ru.egorov.ProfitSeeking.model.Offer;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItem(ProductDto productDto);

    @Mapping(source = "productDto.storeType", target = "storeName")
    Offer toOffer(ProductDto productDto);
}
