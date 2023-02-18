//
//  FilterScreen.swift
//  iosApp
//
//  Created by Данила Еремин on 18.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
enum TypeNutrition: CaseIterable, Identifiable, Hashable{
    static var allCases: [TypeNutrition] {
        return [.noNutrition(), .breakfastAndDinner(), .all(), .onlyBreakfast()]
    }
    
   
    
    var id: Self { self }
    case noNutrition(title: String = "Без питания")
    case breakfastAndDinner(title: String = "Завтрак и ужин")
    case all(title: String = "Полный пансион")
    case onlyBreakfast(title: String = "Только завтрак")
    var title: String{
        switch self{
        case .noNutrition(let title):
            return title
        case .breakfastAndDinner(let title):
            return title
            
        case .all(let title):
            return title
        case .onlyBreakfast(let title):
            return title
        }
    }
}




struct FilterScreen: View {
    @EnvironmentObject var navigator: NavigatorFilter
    @Binding var selectedType: TypeNutrition
    private let listNutrition = TypeNutrition.allCases
    var body: some View {
        NavigationStack(path: $navigator.path){
            Form{
                
                Section(content: {
                    
                    Picker("Питание", selection: $selectedType){
                        ForEach(listNutrition){
                            item in
                            Text(item.title)
                        }
                    }
                    
                    
                }, header: {
                    Text("Основное")
                })
            }
            .navigationTitle("Фильтры")
            
            
        }
    }
}

