package smallMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;

import static smallMod.DefaultMod.makeCardPath;

public class SurroundedByGold extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Deal damage based on amount of gold you have.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SurroundedByGold.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final double MODIFIER = 0.1;

    private static final int DAMAGE = 0;
    private static final int UPGRADE_PLUS_DMG = 5;
    private static final int UPGRADE_PLUS_MAGIC = 2;


    // /STAT DECLARATION/


    public SurroundedByGold() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = 0;
        magicNumber = baseMagicNumber = 0;
    }

    @Override
    public void applyPowers() {
        magicNumber = (int) Math.floor(AbstractDungeon.player.gold * MODIFIER);
        super.applyPowers();
        baseBlock = magicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, baseBlock));
    }



    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
        }
    }
}