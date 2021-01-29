package smallMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;

import static smallMod.DefaultMod.makeCardPath;

public class SmithsDrift extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Deal 8 dmg
     * If you have a upgraded card in your hand, Draw a card (for every upgraded card in your hand)
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(SmithsDrift.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int AMOUNT = 1;

    // /STAT DECLARATION/


    public SmithsDrift() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (shouldDraw()) {
            if (upgraded) {
                AbstractDungeon.actionManager.addToBottom(
                        new DrawCardAction(p, (int) AbstractDungeon.player.hand.group.stream().filter(card -> card.upgraded).count())
                );
            } else {
                AbstractDungeon.actionManager.addToBottom(
                        new DrawCardAction(p, AMOUNT)
                );
            }
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (shouldDraw()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    private boolean shouldDraw() {
        return (int) AbstractDungeon.player.hand.group.stream().filter(card -> card.upgraded && card != this).count() > 0;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}