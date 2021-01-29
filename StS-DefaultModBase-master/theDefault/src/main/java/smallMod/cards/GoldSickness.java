package smallMod.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import smallMod.DefaultMod;
import smallMod.characters.TheDefault;

import static smallMod.DefaultMod.makeCardPath;

// public class ${NAME} extends AbstractDynamicCard
@AutoAdd.Ignore // Remove this line when you make a template. Refer to https://github.com/daviscook477/BaseMod/wiki/AutoAdd if you want to know what it does.
public class GoldSickness extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID(GoldSickness.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");//

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.CURSE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.NONE;  //   since they don't change much.
    private static final CardType TYPE = CardType.CURSE;       //
    public static final CardColor COLOR = CardColor.CURSE;

    private static int COST = -2;

    // /STAT DECLARATION/


    public GoldSickness() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }


    @Override
    public void upgrade() {

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }


}
