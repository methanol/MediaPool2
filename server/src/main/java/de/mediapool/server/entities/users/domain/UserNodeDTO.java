package de.mediapool.server.entities.users.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.NodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NodeEntity
@Getter
@Setter
@ToString
public class UserNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String username;

	@JsonIgnore
	private String password;

	@RelatedTo(type = "HAS_ROLE", direction = Direction.OUTGOING)
	private @Fetch Set<UserRoleNodeDTO> roles = new HashSet<>();

	private Boolean isLocked = false;

	@Override
	public String getType() {
		return "USER";
	}

	public void addRole(String role) {
		UserRoleNodeDTO userRole = new UserRoleNodeDTO();
		userRole.setName(role);
		roles.add(userRole);

	}

	@Override
	public String toString() {
		return "UserNodeDTO [username=" + username + "]";
	}

	public UserNodeDTO(String username, String password, Set<UserRoleNodeDTO> roles, Boolean isLocked) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.isLocked = isLocked;
	}

	public UserNodeDTO(String username, String password) {
		this(username, password, null, false);
		// this.addRole("User");
	}

	public UserNodeDTO() {
		super();
	}

}
