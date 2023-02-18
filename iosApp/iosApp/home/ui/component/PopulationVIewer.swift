//
//  PopulationVIewer.swift
//  iosApp
//
//  Created by Данила Еремин on 18.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PopulationVIewer: View {
    
    
    @EnvironmentObject var homeViewModel: HomeViewModel
    
    var body: some View {
        
        Text("Популярные места")
            .font(.title.bold())
            .padding(.horizontal)
            .padding(.top)
        
        ScrollView(.horizontal, showsIndicators: false){
            LazyHStack(spacing: 16){
                ForEach(homeViewModel.mostPopular, id: \.self){
                    item in
                    PopulationPlace(image: item.image, score: "\(item.rating)", title:item.title, city: item.city)
                       
                }
            }
            .padding(.horizontal)
            
        }
    }
}

struct PopulationVIewer_Previews: PreviewProvider {
    static var previews: some View {
        PopulationVIewer()
    }
}

