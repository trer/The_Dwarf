package smallMod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;

import static smallMod.DefaultMod.makeCardPath;

public class StrikeWhileTheIronIsHot extends CustomCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Deal dmg for each card upgraded this combat
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(StrikeWhileTheIronIsHot.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int DAMAGE = 0;

    private static final int MODIFIER = 3;
    private static final int UPGRADE_MODIFIER = 2;

    // /STAT DECLARATION/


    public StrikeWhileTheIronIsHot() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MODIFIER;
    }


    @Override
    public void applyPowers(){
        magicNumber = (int) AbstractDungeon.player.drawPile.getCardDeck().stream().filter(card -> card.upgrades != 0).count();
        magicNumber += (int) AbstractDungeon.player.discardPile.getCardDeck().stream().filter(card -> card.upgrades != 0).count();
        magicNumber += (int) AbstractDungeon.player.hand.getCardDeck().stream().filter(card -> card.upgrades != 0).count();
        super.applyPowers();
        baseDamage = magicNumber * MODIFIER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MODIFIER);
            initializeDescription();
        }
    }
}
