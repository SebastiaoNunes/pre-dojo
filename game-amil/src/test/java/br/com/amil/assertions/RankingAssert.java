package br.com.amil.assertions;

import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunType;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.PlayerTable;
import br.com.amil.domain.model.ranking.Ranking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RankingAssert {

    private Ranking ranking;
    private Player player;

    public RankingAssert(Ranking ranking) {
        this.ranking = ranking;
    }

    public static RankingAssert ensureThat(Ranking ranking) {
        return new RankingAssert(ranking);
    }

    public RankingAssert forPlayer(Player player) {
        this.player = player;
        return this;
    }

    public RankingAssert shouldHaveMurders(int murders) {
        assertThat("The murders by player is different", ranking.murdersForPlayer(this.player), equalTo(murders));
        return this;
    }

    public RankingAssert shouldHaveDeath(int deaths) {
        assertThat("The death by player is different", ranking.deathsForPlayer(this.player), equalTo(deaths));
        return this;
    }

    public RankingAssert shouldHavePreferredGun(GunType gunType) {
        assertThat("The Preferred Gun is wrong", ranking.preferredGun().value(), equalTo(gunType.value()));
        return this;
    }

    public RankingAssert shouldBeWinner() {
        PlayerTable playerTable = ranking.gimmeWinner();
        Player winnerPlayer = playerTable.getPlayer();
        boolean isWinner = (winnerPlayer.getUUID().equals(player.getUUID()));
        assertThat("The winner is wrong", isWinner, equalTo(true));
        return this;
    }

    public RankingAssert shouldHaveAward(boolean award) {
        PlayerTable winnerPlayer = ranking.gimmeWinner();
        assertThat("Winner without award", winnerPlayer.isAward(), equalTo(award));
        return this;
    }

    public RankingAssert shouldHaveMostMurderSequenceWithoutDying(int sequence) {
        int mostSeq = ranking.mostMurderSequenceWithoutDying();
        assertThat("The sequence is wrong", mostSeq, equalTo(sequence));
        return this;
    }

}
