package com.project.preferences.converter;

import com.project.preferences.model.Favorite;
import com.project.preferences.model.FavoriteDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FavoriteDtoFavoriteConverter implements Converter<Favorite, FavoriteDto> {
    @Override
    public FavoriteDto convert(Favorite source) {
        if (source == null) {
            return null;
        } else {
            FavoriteDto favoriteDto = new FavoriteDto();
            favoriteDto.setFavoriteDish(source.getFavoriteDish());
            favoriteDto.setFavoriteColorHex(source.getFavoriteColorHex());
            favoriteDto.setFavoriteSong(source.getFavoriteSong());
            favoriteDto.setFavoriteDate(source.getFavoriteDate());
            favoriteDto.setFavoriteNumber(source.getFavoriteNumber());
            return favoriteDto;
        }
    }

}
