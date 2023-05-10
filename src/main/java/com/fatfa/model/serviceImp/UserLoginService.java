package com.fatfa.model.serviceImp;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fatfa.exceptions.ErrorCredencialesExpired;
import com.fatfa.model.dto.UsersHelper;
import com.fatfa.model.dto.UsersPojo;
import com.fatfa.model.entity.UsuarioModel;
import com.fatfa.model.repository.IUsersRepository;

@Service
public class UserLoginService implements UserDetailsService {

	@Autowired()
	private IUsersRepository repouser;

	UsersPojo user = new UsersPojo();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.err.println("INIT");
		UsuarioModel datos = repouser.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(
				"La cuenta de usuario <b>" + username + "</b>, no se encontro en nuestros registros."));

		//System.err.println(datos);

		if (datos == null) {
			throw new ErrorCredencialesExpired(
					"La cuenta de usuario <b>" + username + "</b>, no se encontro en nuestros registros.");
		}

		// int diasHabiles = srvValidation.onBuscarDiasVenceContrasenia(username);

		if (!datos.isEstadoCuenta()) {
			throw new ErrorCredencialesExpired("Estimado usuario, su cuenta fue dado de baja.");
		}

//		if (!user.isClaveVencida() || diasHabiles <= 0) {
//			if (diasHabiles <= 0 && datos.isClaveVencida())  srvValidation.onBloquearCuentaClaveExpirada(username,0) ;
//			throw new ErrorCredencialesExpired(
//					"Lo sentimos, su cuenta esta <b>inhabilitado</b> porque infringue nuestras políticas de seguridad al no cambiar su contraseña en el tiempo solicitado <b>(45 dias)</b>.<br><br> Solicita tu activación en <b>DETEL - COPERE</b>.");
//		}

		if (!datos.isEstadoAcceso()) {
			throw new ErrorCredencialesExpired(
					"Estimado usuario, su <b>cuenta</b> se encuentra temporalmente bloqueada por limite de intentos erroneos. <b>Vuelva a intentarlo dentro de 15mts.</b>");
		}

		Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(datos.getPerfil().getDescripcion());
		listOfgrantedAuthorities.add(grantedAuthority);

		user.setUsername(datos.getEmail());
		user.setPassword(datos.getPassword());
		user.setEnabled(datos.isEstadoCuenta());
		user.setAccountNonExpired(datos.isEstadoCuenta());
		user.setAccountNonLocked(datos.isEstadoAcceso());
		user.setCredentialsNonExpired(datos.isEstadoCambioPassword());
		user.setListOfgrantedAuthorities(listOfgrantedAuthorities);

		UsersHelper userDetail = new UsersHelper(user);

		return userDetail;
	}

}
