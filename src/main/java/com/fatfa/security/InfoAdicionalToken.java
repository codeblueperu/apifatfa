package com.fatfa.security;


import java.util.HashMap;
import java.util.Map;


import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;


@Component
public class InfoAdicionalToken implements TokenEnhancer{

//	@Autowired
//	private IUsersService srvuser;
	
	
//	@Autowired
//	LoginValidationService srvValidation;
//	
//	@Autowired
//	private ServiceMenu serviceMenu;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
	//	UsuarioModel user = null;
		try {
			//user = srvuser.findByUsername(authentication.getName());
			
			//DatosMilitaresEntity datosmilitares = srvDatosMilitares.buscarPersonalMilitar(authentication.getName());
			Map<String, Object> response = new HashMap<>();
			
			response.put("STATUS","OK");
//			response.put("grado", datosmilitares.getGrado());
//			response.put("arma", datosmilitares.getArma());
//			response.put("cip", datosmilitares.getCip());
//			response.put("perfil", user.getCodigoPerfil().getDescripcionPerfil());
//			response.put("fechaVigencia", new SimpleDateFormat("dd/MM/yyyy").format(user.getFechaVencimiento()));
//			response.put("perfil", user.getCodigoPerfil().getDescripcionPerfil());
//			response.put("diasHabiles",  srvValidation.onBuscarDiasVenceContrasenia(authentication.getName()));
//			response.put("menus", serviceMenu.getListarMenuPerfil(user.getCodigoPerfil().getCodperfil()));
			
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(response);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return accessToken;
	}
}
