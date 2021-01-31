package smallMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;

import static smallMod.DefaultMod.makeCardPath;

public class HotPotato extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Deal 12 (15) dmg
     * If this is in your hand at the end of the turn, take 3 dmg
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(HotPotato.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DAMAGE = 3;

    private static final int MAGIC = 3;



    // /STAT DECLARATION/


    public HotPotato() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, DAMAGE), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        if (AbstractDungeon.player.hand.group.stream().anyMatch(card -> card == this)) {
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            initializeDescription();
        }
    }
}
