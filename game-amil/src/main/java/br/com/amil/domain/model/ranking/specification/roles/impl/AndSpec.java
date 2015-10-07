package br.com.amil.domain.model.ranking.specification.roles.impl;

import br.com.amil.domain.model.ranking.specification.roles.RoleSpec;

public class AndSpec implements RoleSpec {

    private RoleSpec roleSpecOne;
    private RoleSpec roleSpecTwo;

    public AndSpec(final RoleSpec roleSpecOne, final RoleSpec roleSpecTwo) {
        this.roleSpecOne = roleSpecOne;
        this.roleSpecTwo = roleSpecTwo;
    }

    @Override
    public boolean isSatisfied() {
        return (roleSpecOne.isSatisfied() && (roleSpecTwo.isSatisfied()));
    }


}
