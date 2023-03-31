package com.project.preferences.converter;

import com.project.preferences.model.Favorite;
import com.project.preferences.model.FavoriteDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FavoriteFavoriteDtoConverter implements Converter<FavoriteDto, Favorite> {
    @Override
    public Favorite convert(FavoriteDto source) {
        if (source == null) {
            return null;
        } else {
            Favorite favorite = new Favorite();
            favorite.setFavoriteDish(source.getFavoriteDish());
            favorite.setFavoriteColorHex(source.getFavoriteColorHex());
            favorite.setFavoriteSong(source.getFavoriteSong());
            favorite.setFavoriteDate(source.getFavoriteDate());
            favorite.setFavoriteNumber(source.getFavoriteNumber());
            return favorite;
        }
    }

}