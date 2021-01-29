package smallMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.brashmonkey.spriter.Player;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import smallMod.DefaultMod;
import smallMod.util.TextureLoader;

import static smallMod.DefaultMod.makeRelicOutlinePath;
import static smallMod.DefaultMod.makeRelicPath;

public class BloodOfIron extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Every time you take damage, gain Metalizse or plated armor
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("BloodOfIron");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Blood_Of_Gold.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Blood_Of_Gold.png"));

    public BloodOfIron() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }


    // "Whenever you take damage, gain plated Armor."
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
        AbstractPlayer p = AbstractDungeon.player;
        if (damageAmount > 0) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 1)));
        }

    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("BloodOfGold");
    }
}
