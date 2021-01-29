package smallMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;
import smallMod.powers.FreeCardPower;

import static smallMod.DefaultMod.makeCardPath;

public class PortableForge extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Upgrade ALL cards in your hand. (innate)
     * Exhaust
     * Retain
     * Put a Portable Forge into your hand
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(PortableForge.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;



    // /STAT DECLARATION/


    public PortableForge() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.selfRetain = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ArmamentsAction(true));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.makeCopy()));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.isInnate = true;
            initializeDescription();
        }
    }
}
