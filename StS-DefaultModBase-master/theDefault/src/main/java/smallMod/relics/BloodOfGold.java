package smallMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import smallMod.DefaultMod;
import smallMod.util.TextureLoader;

import static smallMod.DefaultMod.makeRelicOutlinePath;
import static smallMod.DefaultMod.makeRelicPath;

public class BloodOfGold extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Every time you take damage, gain that much gold.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("BloodOfGold");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Blood_Of_Gold.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Blood_Of_Gold.png"));

    public BloodOfGold() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }


    // "Whenever you take damage, gain (that much) gold."
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    /**
     * This is called every time you lose hp by an outside class.
     * Gold is calculated on actual damage taken.
     * @param damageAmount The damage that is being dealt to your character.
     */
    @Override
    public void onLoseHp(int damageAmount) {
        if (damageAmount > 0) {
            this.flash();
            AbstractDungeon.effectList.add(new RainingGoldEffect(damageAmount * 2, true));
            this.addToBot(new GainGoldAction(damageAmount));
        }

    }
}
