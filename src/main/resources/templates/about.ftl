[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title='about' /]

<body>

    [@navigationUtil.navigation title='poelspel' back='/' bgColor='bg-greybat' /]

    <div class="grid grid-gutter bg-white about">
        <div class="component">
            <h1 class="h4 about__title"><span class="c-greybat">Het spel</span></h1>
            <p class="c-green about__text">
                Chia whatever lumbersexual, gentrify poutine drinking vinegar banjo gluten-free sustainable yr. Keffiyeh hella chia single-origin coffee, schlitz chambray wolf. Crucifix chillwave microdosing letterpress, deep v man braid asymmetrical cardigan blog. Knausgaard paleo slow-carb organic selfies flexitarian, ramps health goth seitan tacos microdosing truffaut. Bespoke +1 trust fund, butcher dreamcatcher stumptown street art. Godard bushwick street art polaroid microdosing, brooklyn 3 wolf moon cray selfies ramps pour-over. Craft beer mixtape viral green juice disrupt tofu cold-pressed, yuccie XOXO.
            </p>

            <h1 class="h4 c-greybat about__title"><span class="c-greybat">Je profiel</span></h1>
            <p class="c-green about__text">
                Franzen artisan deep v four dollar toast gastropub, yuccie sartorial swag squid direct trade tacos YOLO chambray aesthetic narwhal. Tacos twee cray lumbersexual mumblecore. Whatever literally brooklyn beard, YOLO organic pug cronut. Literally migas organic portland typewriter man braid. Try-hard kale chips bitters humblebrag, chia drinking vinegar etsy iPhone. Put a bird on it austin affogato bicycle rights disrupt. Thundercats 3 wolf moon 90's tote bag venmo 8-bit.
            </p>
        </div>
    </div>
    [@footerUtil.footer /]
</body>
</html>