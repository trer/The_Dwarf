package smallMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;

import static smallMod.DefaultMod.makeCardPath;

public class SpikeStrike extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Deal damage based on amount of Thorns you have
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SpikeStrike.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final double MODIFIER = 2;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 4;



    // /STAT DECLARATION/


    public SpikeStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
        magicNumber = baseMagicNumber = 0;
    }

    @Override
    public void applyPowers() {
        long thorns = AbstractDungeon.player.powers.stream().filter(power -> power instanceof ThornsPower).mapToLong(power -> power.amount).findAny().orElse(0);
        magicNumber = (int) Math.floor(thorns * MODIFIER);
        super.applyPowers();
        damage += magicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }



    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }
}