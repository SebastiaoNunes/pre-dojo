package br.com.amil.domain.model.ranking.specification.creation;

import br.com.amil.domain.model.ranking.History;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.PlayerTable;
import br.com.amil.domain.model.ranking.specification.creation.impl.CreatorSpecImpl;
import br.com.amil.domain.model.ranking.specification.roles.shared.Roles;




import static br.com.amil.domain.model.ranking.builder.PlayerTableBuilder.createTable;

public class CreatorSpecMatcher {

    public static CreatorSpec aPlayerTable(Player player, Roles role) {
        return new CreatorSpecImpl(player, role);
    }
}
