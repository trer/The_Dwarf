package smallMod.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;

import static smallMod.DefaultMod.makeCardPath;

// public class ${NAME} extends AbstractDynamicCard
@AutoAdd.Ignore
public class CrackTheBarrel extends AbstractDynamicCard {



    public static final String ID = DefaultMod.makeID(CrackTheBarrel.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("${NAME}.png");


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;  // COST = ${COST}

    private static final int DAMAGE = 3;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_DAMAGE = 5;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /STAT DECLARATION/

    public CrackTheBarrel() {
        this(0);
    }

    public CrackTheBarrel(int upgrades) {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.timesUpgraded = upgrades;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(
                new DrawCardAction(p, magicNumber));
    }


    public void upgrade() {
        this.upgradeDamage(UPGRADE_DAMAGE);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    public boolean canUpgrade() {
        return true;
    }
}
