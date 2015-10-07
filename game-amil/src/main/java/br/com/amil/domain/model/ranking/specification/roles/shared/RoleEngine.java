package br.com.amil.domain.model.ranking.specification.roles.shared;

import br.com.amil.domain.model.ranking.PlayerTable;
import br.com.amil.domain.model.ranking.specification.creation.CreatorSpec;
import br.com.amil.domain.model.ranking.specification.roles.RoleSpec;

public class RoleEngine {

   private RoleSpec roleSpec;
   private PlayerTable playerTable;

   public static RoleEngine toTablePlayer(String desc) {
       return new RoleEngine();
   }

   public RoleEngine caseForValidRole(RoleSpec roleSpec, CreatorSpec creatorSpec) {
       this.roleSpec = roleSpec;
       if (!alreadyCreated() && roleSpec.isSatisfied()) {
           this.playerTable = creatorSpec.create();
       }
       return this;
   }

   private boolean alreadyCreated() {
       return (playerTable != null);
   }

   public PlayerTable create() {
       return playerTable;
   }
}
