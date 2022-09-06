package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnessapp.Adapters.TipsListAdapter;
import com.example.fitnessapp.Adapters.VegItemListAdapter;
import com.example.fitnessapp.Model.Recipes;
import com.example.fitnessapp.Model.VegeItems;

import java.util.ArrayList;

public class VegetableSubItems extends AppCompatActivity {

    private TextView nameTxt;
    private ImageView back;
    private RecyclerView recyclerView;
    private TipsListAdapter adapter;
    private ArrayList<Recipes> tipList;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_sub_items);
        back = (ImageView)findViewById(R.id.back);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(VegetableSubItems.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        tipList = new ArrayList<>();
        nameTxt = (TextView) findViewById(R.id.title);
        title = getIntent().getStringExtra("title");
        nameTxt.setText(title);
        setItems();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FoodRecipes.class));
            }
        });
    }

    //Store Vegetables Recipes in a list
    private void setItems() {
        if (title.equals("Vegetables")){
            tipList.add(new Recipes("Garlic mushroom stuffed peppers",
                    "1 Preheat the oven to 220ºC, gas mark 7. Place the peppers, cut-side up in a roasting tin, brush with a little of the oil and season lightly. Bake for 25 minutes.\n" +
                            "\n" +
                            "2 Heat the remaining oil in a large frying pan and fry the fennel for 5 minutes. Stir in the garlic, then the mushrooms and fry gently for 6-8 minutes until the mushrooms are soft and the juices have evaporated.\n" +
                            "\n" +
                            "3 Reserve a few small basil leaves. Tear the remainder into pieces and stir into the mushroom mixture with the fennel seeds and olives. Spoon into the red peppers and return to the oven for 5 minutes. Blend the balsamic glaze with 1 tbsp water and drizzle over the plate. Scatter with the reserved basil leaves. Delicious served with a watercress or rocket salad.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/g/WRWK17052018_GARLIC-MUSHROOM-STUFFED-PEPPERS.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Aubergine, rice noodles and peanut dressing",
                    "1. Halve the aubergine lengthways, then cut each half into 3 long wedges. Halve these long wedges widthways to make shorter wedges and toss in a bowl with 2 tsp sunflower oil and a pinch of salt. Heat a large frying pan over a high heat. Add the aubergine and fry on each side for 3 minutes until tender and golden all over.\n" +
                            "\n" +
                            "2. Meanwhile, make the dressing by whizzing the soy sauce, vinegar, peanut butter and honey in a small blender until smooth.\n" +
                            "\n" +
                            "3. Remove the aubergine from the pan and set aside. Add the noodles and remaining 1 tsp oil to the pan and fry, tossing, for 2-3 minutes until heated through. Take off the heat and toss through the carrot and sugar snaps. Divide between plates, top with the aubergine and salad onions, and spoon over the dressing.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/a/Auberginericenoodles.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Veggie sausage & mixed grain cassoulet",
                    "1. Heat the oil in a large saucepan and briefly fry the sausages until browned, about 3 minutes. Lift them out of the pan and set aside. Add the onions and fry for 5 minutes, adding the crushed garlic for the last couple of minutes. Halve the sausages lengthways.\n" +
                            "\n" +
                            "2. Drain the beans and add to the pan. Fill the empty can with water and add to the pan with the rosemary, cloves, tomato paste, sausages and a little seasoning. Bring to a gentle simmer, cover and cook for 15 minutes.\n" +
                            "\n" +
                            "3. Stir in the mixed grains and tomatoes and cook for a further 5 minutes until very hot.  ",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/v/Veggie%20sausage%20mixed%20%20grain%20cassoulet.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
        }
        else if (title.equals("Fruits")){
            tipList.add(new Recipes("Gluten-free pistachio pancakes with vanilla-cardamom strawberries",
                    "1. Put all the ingredients for the strawberries into a small pan over a low-medium heat and bring to a simmer. Cook gently for 8-10 minutes until the strawberries are softened but still holding their shape, then set aside.\n" +
                            "\n" +
                            "2. Meanwhile, pulse the seeds and pistachios in the small bowl of a food processor, until roughly chopped. Reserve 50g, then whizz the rest with the porridge oats until finely ground. Tip into a large bowl and stir in 300ml yogurt, the milk, eggs, baking powder and bicarbonate of soda.\n" +
                            "\n" +
                            "3. Heat 1 tsp oil in a heavy- based frying pan and, once hot, add 2 heaped dessert spoons of batter per pancake (you should be able to make 3-4 at a time, being careful not to overfill the pan). Reduce the heat to medium and cook for 4-5 minutes, until bubbles appear and the tops of the pancakes start to set. Flip and cook for 3-4 minutes, until browned and set. Keep warm and repeat with the remaining batter. Serve with a dollop of the remaining yogurt, the strawberries (avoid the whole spices), and the reserved seeds and nuts scattered over.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/g/Gluten-free-pistacho-pancakes.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Blueberry soup with yogurt and toasted oats",
                    "1. Put the blueberries, lemon juice, maple syrup, cinnamon, cardamom and 300ml water in a medium saucepan. Add a pinch of salt, bring to the boil then simmer for 10 minutes. Take off the heat, discard the cinnamon and cardamom, then very roughly crush the blueberries using a potato masher or the back of a fork. Set aside to cool, then chill.\n" +
                            "\n" +
                            "2. Preheat the oven to 180˚C, gas mark 4. Toss the oats, almonds, desiccated coconut, maple syrup and a small pinch of salt together. Spread out on a small parchment-lined baking tray and bake for 8-10 minutes, stirring halfway, until just turning golden; set aside to cool (or serve warm, if liked).\n" +
                            "\n" +
                            "3. When ready to serve, divide the soup between bowls, add spoonfuls of yogurt or non-dairy yogurt alternative and sprinkle over the oat mixture.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/b/Blueberry-soup-with-yogurt.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Salmon, avocado & poached egg muffins",
                    "1. Set a large frying or sauté pan over a low heat and pour in a kettle full of just-boiled water. The water should be hot and steaming but with no bubbles rising to the surface. One at a time, and keeping them well spaced apart, crack in the 4 eggs and poach for 2-3 minutes, until the whites are set but the yolks are still soft. Lift out with a slotted spoon and set aside to drain on kitchen paper.\n" +
                            "\n" +
                            "2. Meanwhile, split open the muffi ns and toast. Divide between plates and arrange slices of avocado and smoked salmon on top of each half, and then a poached egg. In a bowl, swirl the chilli sauce through the yogurt and spoon over the eggs. Serve with the lime wedges to squeeze over.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/s/Salmon-&-egg-muffins.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
        }
        else if (title.equals("Nuts And Seed")){
            tipList.add(new Recipes("Baked pear, almond & oat squares",
                    "1. Preheat the oven to 200˚C, gas mark 6. Line a 21cm square cake tin with parchment. Mix the oats, ground almonds, baking powder, cinnamon, salt and apricots in a large mixing bowl. In a jug, whisk together the Alpro oat drink, egg, mashed bananas, maple syrup and almond extract.\n" +
                            "\n" +
                            "2. Pour the oat drink mixture into the bowl of dry ingredients. Peel, halve and core both pears. Cut 1 pear into 1cm dice and stir through the mixture. Tip the mixture into the cake tin, smoothing the top.\n" +
                            "\n" +
                            "3. Slice the other pear and scatter over the top. Bake for 45 minutes, then cool completely before cutting into squares.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/b/baked-pear-&-oat-squares.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Cashew, banana and cacao bars",
                    "1. Preheat the oven to 180°C, gas mark 4. Line a 20cm non-stick tin with a strip of baking parchment. Set aside 1 tbsp of the cashew nuts, put the rest in a food processor and whizz until finely chopped.\n" +
                            "\n" +
                            "2. Break in the bananas and add the oats, cacao powder, bicarbonate of soda and vanilla extract. Whizz for 2-3 minutes until fairly smooth, then pulse in ½ of the cacao nibs. Transfer the mixture to the prepared tin and level the surface with the back of a spoon.\n" +
                            "\n" +
                            "3. Finely chop the reserved cashew nuts and sprinkle over the top with the remaining cacao nibs. Bake for 20-25 minutes until set and golden. Leave to cool completely, then cut into 16 bars. Store in the fridge in an airtight container for up to 5 days. \n" +
                            "\n",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/c/Cashew-and-cacao-bars.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Peanut and pecan granola",
                    "1. Preheat the oven to 150°C, gas mark 2. Mix the peanut butter, sunflower oil and maple syrup together in a large mixing bowl, then stir through all the remaining ingredients apart from the dried blueberries.\n" +
                            "\n" +
                            "2. Tip the mixture onto a large parchment-lined baking tray; spread in an even layer. Bake for 40 minutes, stirring halfway, until lightly crisp and golden. Stir through the blueberries, then return to the oven for a final 5 minutes; set aside to cool completely. Store in an airtight container for up to 1 month.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/p/Peanut-and-pecan-granola.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
        }
       /* else if (title.equals("Herbal Tea")){
            tipList.add(new Recipes("",
                    "",
                    ""));
            tipList.add(new Recipes("",
                    "",
                    ""));
            tipList.add(new Recipes("",
                    "",
                    ""));
        }*/
        else if (title.equals("Chicken")){
            tipList.add(new Recipes("Jamaican jerk chicken with rice & beans salad",
                    "1. Preheat oven to 200°C, gas mark 6. Place the chicken in a large bowl and mix in the jerk sauce. Place the chicken on a large baking tray, reserving the marinade and roast for 25-30 minutes or until cooked throughout with no pink meat.\n" +
                            "\n" +
                            "2. Meanwhile, blanch the sweetcorn in boiling water for 5 minutes, drain and coat in the reserved marinade.\n" +
                            "\n" +
                            "3. Bring the coconut milk and 300ml water to the boil in a large saucepan and add the rice, cover and cook gently for 5 minutes, stirring occasionally. Add the beans and cook for a further 5-7 minutes until the rice is tender and the liquid has been absorbed. Allow to cool, then stir in the salad onions and coriander. Whisk together the lime zest and juice and mayonnaise, then stir into the salad and season to taste.\n" +
                            "\n" +
                            "4. Place the chicken and sweetcorn on the barbecue for 5 minutes, turning occasionally until slightly charred, then serve with the salad.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/j/WRWK25052017_Jamaican-Jerk-Chicken-Rice-Beans-Salad.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Barbecue chicken with simple slaw",
                    "1. In a large bowl, mix together the ginger, ketchup and Worcestershire sauce. Slash the chicken thighs with a small sharp knife then add to the bowl and mix until evenly coated, making sure the sauce goes into the slashes.\n" +
                            "\n" +
                            "2. Grill for 25 minutes, turning once or twice until nicely browned and cooked through without any pink meat.\n" +
                            "\n" +
                            "3. Meanwhile, mix together the cabbage, carrot, radishes and salad onions. Add the dressing and nigella seeds and toss together well.\n" +
                            "\n" +
                            "4. Serve the barbecue chicken with the simple slaw and flatbread.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/b/201307r07.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Chicken Kebabs with Roasted Red Onions",
                    "1. Preheat the oven to 200ºC, gas mark 6. If using wooden skewers, soak 4 in cold water for 20 minutes before using to prevent them from burning. Mix 1 tbsp of the balsamic vinegar with the lemon zest and juice, together with the garlic, honey and half the oregano. Add the chicken, and coat well in the marinade. If you have time, leave in the fridge for 20–30 minutes to marinate.\n" +
                            "2. Meanwhile, place the tomatoes in a small roasting dish with half the red onion pieces. Drizzle over 2 tbsp balsamic vinegar, add some seasoning and the remaining oregano and toss well. Roast in the oven for 20–25 minutes until tender.\n" +
                            "3. Preheat the grill to high. Make the kebabs by alternating the chicken and the remaining red onion pieces onto the skewers. Cook the chicken on the grill rack, turning regularly for about 18–20 minutes until the meat is thoroughly cooked and the juices run clear with no pink meat.\n" +
                            "4. Serve the kebabs with the roasted onions and tomatoes together with some essential Waitrose Easy Cook Long Grain Rice, and green salad leaves.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/c/0906r2.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
        }
       /* else if (title.equals("Mutton")){
            tipList.add(new Recipes("",
                    "",
                    ""));
            tipList.add(new Recipes("",
                    "",
                    ""));
            tipList.add(new Recipes("",
                    "",
                    ""));
        }*/
        else if (title.equals("Beef")){
            tipList.add(new Recipes("American-style roast beef (barbecued brisket)",
                    "1. Remove all string from the beef and unroll. Lay the meat out flat in a non-metallic dish and coat completely with the steak rub. Cover and leave in the fridge for at least 12 hours, or for up to 24 hours.\n" +
                            "\n" +
                            "2. Preheat the oven to 150°C, gas mark 2. Place all of the sauce ingredients into a large roasting tin big enough to hold the meat in a single layer. Season with black pepper only. Mix well, then add the beef and any juices to the tin. Spoon the sauce over the meat, then lay a piece of scrunched-up, damp baking parchment on top, followed by a large single piece of foil. Seal tightly around the edges of the tin. Place in the oven for 3 hours. Remove from the oven and leave covered in the tin to cool completely at room temperature.\n" +
                            "\n" +
                            "3. Prepare and light the barbecue, then leave until the coals are white. Remove the meat from the sauce and place it onto the barbecue rack. Cook for 20 minutes, turning the meat very frequently, until piping hot throughout.\n" +
                            "\n" +
                            "4. Meanwhile, bring the sauce in the roasting tin to the boil over a high heat and simmer  vigorously for 20 minutes, or until thickened and reduced by about two-thirds. Strain through a sieve into a jug, pressing well with the back of a spoon to release all the juices. Serve the meat thinly sliced, with lots of sauce to spoon over.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/a/American-style-roast-beef-(barbecued-brisket).jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Barbecue beef brisket sandwiches",
                    "1 Preheat the oven to 200 ̊C, gas mark 6. Prepare the brisket according to pack instructions: empty the meat and liquor into the foil tray provided and cover with foil. Cook for 20 minutes, then uncover and drain off any liquid. Smother with the barbecue sauce and cook for 10 minutes more. If the ciabatta rolls require baking (check pack instructions), place them in the oven for 10 minutes.\n" +
                            "\n" +
                            "2 Meanwhile, make the slaw. Stir together the lime juice and mayonnaise in a large bowl, then toss in the cabbage, apple and salad onions; season.\n" +
                            "\n" +
                            "3 Remove the brisket from the oven and cut into 9 slices. Split open the rolls and put 3 slices on the base of each. Top with a slice of cheese and pop back in the oven for a minute or two to melt. Once melted, spoon on the slaw and finish with the bun tops. Serve with the remaining slaw on the side. ",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/b/brisket-sandwiches.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Beef ribs with barbecue glaze",
                    "1. To prepare the ribs, place 1 litre cold water in a pan and warm over a medium heat. Add the salt and stir until dissolved. Remove from the heat and allow to cool by placing the pan in a bowl full of iced water.\n" +
                            "\n" +
                            "2. Warm the whiskey in a small pan over a medium heat. When it comes to a boil, carefully ignite it with a match. When the flames disappear, simmer until reduced slightly. Allow to cool completely, then add it to the salt brine.\n" +
                            "\n" +
                            "3. Pour the cool brine in a large airtight container, add the short ribs and seal with the lid. Put in the fridge for 12 hours. Remove the ribs from the brine and place into a bowl of cold, fresh water. Allow to soak for 3 hours, changing the water every 30 minutes.\n" +
                            "\n" +
                            "4. Place the ribs into a large pan. Add the stock and enough cold water to cover the meat. Warm over a medium heat. Bring to the boil, then reduce the heat and simmer gently for 3 hours, or until the meat is tender. Turn off the heat and leave the meat to cool in the stock. When cool, remove the meat from the stock and set aside.\n" +
                            "\n" +
                            "5. Return the pan of stock to a high heat and bring to the boil until reduced by two-thirds, skimming the surface to remove any foam throughout. Remove from the heat and set aside. Reserve 600g of stock for the barbecue sauce.\n" +
                            "\n" +
                            "6. Next prepare the barbecue sauce. Place the oil in a saucepan over a medium heat. Add the garlic and shallots and sweat for 2-3 minutes until soft. Add the dry spices and continue to cook for 2 minutes, stirring throughout. Mix in the tomato passata, vinegar, sugar, Worcestershire sauce, golden syrup, salt and pepper. Reduce the heat to low and cook for 20 minutes, then stir in the reduced stock.\n" +
                            "\n" +
                            "7. Preheat the barbecue. When hot, add the ribs and cook, turning often and basting with the barbecue sauce, for 3-5 minutes, until browned. Move the ribs to the coolest part of the barbecue and cook for a further 30 minutes, turning often and basting with the sauce throughout, until glazed and piping hot. Warm any remaining barbecue sauce and serve with the ribs.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/b/Waitrose-Weekend_WK360_Heston-BBQ-Menu_Beef-Ribs-BBQ-Glaze.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
        }
        else if (title.equals("Fish")){
            tipList.add(new Recipes("Piri piri grilled sardines with lemon & fennel salad",
                    "\n" +
                            "1. Light the barbecue or heat a griddle pan to hot.\n" +
                            "\n" +
                            "2. To make the sauce, place the peppers and chilli on the barbecue or griddle, cook until blackened and soft, turning frequently. Remove from the heat and leave to cool. Once cool, peel, split in half and remove the seeds and stalks. For extra heat, leave the seeds in the chilli. Place the pepper and chilli into a small bowl food processor along with the garlic, oregano, paprika, vinegar and oil and blitz until smooth.\n" +
                            "\n" +
                            "3. Put a third of the piri piri sauce in a separate bowl and use to brush over the sardines. Reserve the rest. Cook the sardines over the barbecue or grill for 3 minutes on each side until the skin is crisp and the flesh falls away from the bone.\n" +
                            "\n" +
                            "4. To make the salad, toss the lemon, fennel, parsley and avocado oil together then serve with the cooked sardines and reserved sauce.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/p/Waitrose-Weekend_266_Menu_Fri-Night_Piri-Piri-Sardines-&-Fennel-Salad.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Barbecued sea bass with tangy corn & tomatoes",
                    "1. Preheat the grill to medium with a grill pan on the top rack. Meanwhile, coat the sea bass with 2 tbsp of the barbecue sauce. Cover and chill to marinate.\n" +
                            "\n" +
                            "2. Brush the sweetcorn with the oil and put on the hot grill pan. Cook for 15 minutes, turning halfway through. Mix the feta with the lime zest\n" +
                            "and juice, then season and set aside. Season the tomatoes and set aside.\n" +
                            "\n" +
                            "3. Transfer the corn to a dish and immediately top with the feta mixture. Cover and set aside. Grill the fish, skin-side up, for 5 minutes (no need to flip) until cooked through and opaque. Drizzle the fish with the remaining barbecue sauce, then serve with the corn, tomatoes and lime wedges to squeeze over.  ",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/b/BBQSeaBass.jpg/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
            tipList.add(new Recipes("Barbecued lime & chilli tiger prawns",
                    "1. Combine the lime zest, chillies, garlic, fish sauce and olive oil in a large bowl and whisk to combine. Peel the middle sections of the shells from the prawns, leaving the heads and tails intact, and de-vein. Add the prawns to the marinade and toss to coat. Cover and refrigerate for 30 minutes.\n" +
                            "\n" +
                            "2. Preheat the barbecue or grill to a high heat. Thread the prawns on to skewers then grill for 3-4 minutes. Turn, brush with the melted butter, and cook for a further 3-4 minutes. Remove from the heat and brush with any remaining butter. Serve with lime wedges.",
                    "https://www.waitrose.com/content/dam/waitrose/recipes/images/b/BBQ-Tiger-Prawns.gif/_jcr_content/renditions/cq5dam.thumbnail.400.400.png"));
        }
        adapter = new TipsListAdapter(VegetableSubItems.this,tipList);
        recyclerView.setAdapter(adapter);
    }
}