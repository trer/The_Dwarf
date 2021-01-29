package smallMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;

import static smallMod.DefaultMod.makeCardPath;

public class Feast extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * For Each Loop x2" "Apply 1 Vulnerable to all enemies, 2(3) times.
     */

    // TEXT DECLARATION 

    public static final String ID = DefaultMod.makeID(Feast.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/

    
    // STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int HEAL = 9;
    private static final int UPGRADE_HEAL = 3;

    private static final int WEAK_AMOUNT = 2;
    private static final int UPGRADE_WEAK_AMOUNT = -1;


    // /STAT DECLARATION/

    
    public Feast() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = HEAL;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = WEAK_AMOUNT;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p ,magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, WEAK_AMOUNT, false)));

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADE_HEAL);
            this.upgradeDefaultSecondMagicNumber(UPGRADE_WEAK_AMOUNT);
            initializeDescription();
        }
    }
}