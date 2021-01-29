package smallMod.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;

import static smallMod.DefaultMod.makeCardPath;

// public class ${NAME} extends AbstractDynamicCard
public class GoldOffering extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(GoldOffering.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("${NAME}.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.



    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final int COST = 0;  // COST = ${COST}

    private static final int DAMAGE = 5;    // DAMAGE = ${DAMAGE}

    private static final int DRAW_AMOUNT = 3;
    private static final int UPGRADE_DRAW_AMOUNT = 1;

    // /STAT DECLARATION/


    public GoldOffering() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DAMAGE;
        magicNumber = baseMagicNumber = DRAW_AMOUNT;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new OfferingEffect(), 0.1F));
        } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new OfferingEffect(), 0.5F));
        }
        AbstractDungeon.actionManager.addToBottom(
                new LoseHPAction(p, p, defaultSecondMagicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new DrawCardAction(p, magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            upgradeMagicNumber(UPGRADE_DRAW_AMOUNT);
            initializeDescription();
        }
    }
}
