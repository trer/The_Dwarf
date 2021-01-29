package smallMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import smallMod.DefaultMod;
import smallMod.util.TextureLoader;

import static smallMod.DefaultMod.makeRelicOutlinePath;
import static smallMod.DefaultMod.makeRelicPath;

public class GoldSickness extends CustomRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * At the start of each combat, gain 1 Strength (i.e. Vajra)
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("GoldSickness");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic2.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic2.png"));

    private static boolean NEWSHOP = true;
    private AbstractCard curse = new Doubt();

    public GoldSickness() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.FLAT);

    }

    @Override
    public void onSpendGold() {
        if (AbstractDungeon.getCurrRoom() instanceof ShopRoom && NEWSHOP) {
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(this.curse, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            NEWSHOP = false;
        }
        super.onSpendGold();
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        super.onEnterRoom(room);
        if (AbstractDungeon.getCurrRoom() instanceof ShopRoom) {
            NEWSHOP = true;
        }
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
