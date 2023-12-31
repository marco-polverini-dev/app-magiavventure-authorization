package app.magiavventure.authorization.mapper;

import app.magiavventure.authorization.configuration.AuthorizationProperties.ErrorProperties.ErrorMessage;
import app.magiavventure.authorization.model.HttpError;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorizationErrorMapper {
    HttpError map(ErrorMessage errorMessage);
}
